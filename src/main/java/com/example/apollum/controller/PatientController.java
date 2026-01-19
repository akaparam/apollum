package com.example.apollum.controller;

import com.example.apollum.models.contracts.*;
import com.example.apollum.service.PatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController()
@RequestMapping(value = "/api/v1/patients", produces = "application/json")
@Tag(name = "Patients", description = "Endpoints for managing & profiling patients")

public class PatientController {

    @Autowired
    private PatientService patientServ;

    @PostMapping("/")
    public ResponseEntity<CreatePatientResponse> create(@RequestBody CreatePatientRequest request) {
        CreatePatientResponse response = patientServ.create(request);

        return ResponseEntity
                .created(URI.create("/api/v1/patients" + response.patientId()))
                .body(response);
    }

    @GetMapping("/{patientId}") // Get
    public ResponseEntity<GetPatientResponse> read(@PathVariable UUID patientId) {
        GetPatientResponse response = patientServ.get(patientId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{patientId}") // Update
    public ResponseEntity<Void> update(@PathVariable UUID patientId, @RequestBody UpdatePatientRequest request) {
        patientServ.update(patientId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{patientId}") // Delete
    public ResponseEntity<Void> delete(@PathVariable UUID patientId) {
        patientServ.delete(patientId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search") // Search
    public ResponseEntity<Page<GetPatientResponse>> search(
            @RequestBody SearchPatientRequest request,
            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<GetPatientResponse> result = patientServ.search(request, pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{patientId}/insurance")
    public ResponseEntity<HasInsuranceResponse> isInsured(@PathVariable UUID patientId) {
        HasInsuranceResponse response = patientServ.hasInsurance(patientId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{patientId}/insurance")
    public ResponseEntity<CreateInsuranceResponse> createInsurance(@PathVariable UUID patientId, @RequestBody CreateInsuranceRequest request) {
        CreateInsuranceResponse response = patientServ.addInsurance(patientId, request);

        return ResponseEntity
                .created(URI.create("/api/v1/patients/" + patientId + "/insurance"))
                .body(response);
    }

    @DeleteMapping("/{patientId}/insurance")
    public ResponseEntity<Void> deleteInsurance(@PathVariable UUID patientId) {
        patientServ.removeInsurance(patientId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{patientId}/appointments")
    public ResponseEntity<Page<GetAppointmentResponse>> list(
            @PathVariable UUID patientId,
            @PageableDefault(size = 5, sort = "startTime", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<GetAppointmentResponse> response = patientServ.getAllAppointments(patientId, pageable);

        return ResponseEntity.ok(response);
    }

}
