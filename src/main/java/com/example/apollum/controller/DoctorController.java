package com.example.apollum.controller;

import com.example.apollum.models.contracts.*;
import com.example.apollum.service.DoctorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/doctors", produces = "application/json")
@Tag(name = "Doctors", description = "Endpoints for managing & profiling doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorServ;

    @PostMapping
    public ResponseEntity<CreateDoctorResponse> create(
            @RequestBody CreateDoctorRequest request
    ) {
        CreateDoctorResponse response = doctorServ.create(request);
        return ResponseEntity
                .created(URI.create("/api/v1/doctors/" + response.doctorId()))
                .body(response);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<GetDoctorResponse> read(@PathVariable UUID doctorId) {
        GetDoctorResponse response = doctorServ.get(doctorId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{doctorId}/specialization")
    public ResponseEntity<Void> updateSpecialization(
            @PathVariable UUID doctorId,
            @RequestBody UpdateDoctorSpecializationRequest request
    ) {
        doctorServ.updateSpec(doctorId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<Void> delete(@PathVariable UUID doctorId) {
        doctorServ.delete(doctorId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<Page<GetDoctorResponse>> search(
            @RequestBody SearchDoctorsRequest request,
            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<GetDoctorResponse> page = doctorServ.searchDoctors(request, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{doctorId}/departments")
    public ResponseEntity<List<GetDepartmentResponse>> listDepartments(@PathVariable UUID doctorId) {
        List<GetDepartmentResponse> departments = doctorServ.listEnrolledDepartments(doctorId);
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<Page<GetAppointmentResponse>> listAppointments(
            @PathVariable UUID doctorId,
            @PageableDefault(size = 5, sort = "startTime", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<GetAppointmentResponse> page = doctorServ.getAllAppointments(doctorId, pageable);
        return ResponseEntity.ok(page);
    }

}
