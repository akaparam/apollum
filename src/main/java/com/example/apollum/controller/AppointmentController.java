package com.example.apollum.controller;

import com.example.apollum.models.contracts.*;
import com.example.apollum.service.AppointmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/appointments", produces = "application/json")
@Tag(name = "Appointments", description = "Endpoints for managing Doctor / Patient Appointments")

public class AppointmentController {

    @Autowired
    private AppointmentService appointmentServ;

    @PostMapping
    public ResponseEntity<CreateAppointmentResponse> create(
            @Validated @RequestBody CreateAppointmentRequest request
    ) {
        CreateAppointmentResponse created = appointmentServ.create(request);

        return ResponseEntity
                .created(URI.create("/api/v1/appointments/" + created.appointmentId()))
                .body(created);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<GetAppointmentResponse> read(@PathVariable UUID appointmentId) {
        GetAppointmentResponse response = appointmentServ.get(appointmentId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{appointmentId}/slot")
    public ResponseEntity<Void> updateSlot(
            @PathVariable UUID appointmentId,
            @RequestBody UpdateAppointmentSlotRequest request
    ) {
        appointmentServ.updateSlot(appointmentId, request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{appointmentId}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable UUID appointmentId,
            @RequestBody UpdateAppointmentStatusRequest request
    ) {
        appointmentServ.updateStatus(appointmentId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> delete(@PathVariable UUID appointmentId) {
        appointmentServ.delete(appointmentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<Page<GetAppointmentResponse>> search(
            @RequestBody SearchAppointmentRequest request,
            @PageableDefault(size = 5, sort = "startTime", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<GetAppointmentResponse> page = appointmentServ.search(request, pageable);
        return ResponseEntity.ok(page);
    }
}
