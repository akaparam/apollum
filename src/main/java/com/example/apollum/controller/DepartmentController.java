package com.example.apollum.controller;

import com.example.apollum.models.contracts.*;
import com.example.apollum.service.DepartmentService;
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
@RequestMapping(value = "/api/v1/departments", produces = "application/json")
@Tag(name = "Departments", description = "Endpoints for managing departments and members")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentServ;

    @PostMapping
    public ResponseEntity<CreateDepartmentResponse> create(
            @RequestBody CreateDepartmentRequest request
    ) {
        CreateDepartmentResponse response = departmentServ.create(request);

        return ResponseEntity
                .created(URI.create("/api/v1/departments/" + response.departmentId()))
                .body(response);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<GetDepartmentResponse> get(@PathVariable UUID departmentId) {
        GetDepartmentResponse response = departmentServ.get(departmentId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{departmentId}/headDoctor")
    public ResponseEntity<Void> updateHeadDoctor(
            @PathVariable UUID departmentId,
            @RequestBody UpdateDepartmentHeadDoctorRequest request
    ) {
        departmentServ.updateHeadDoctor(departmentId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> delete(@PathVariable UUID departmentId) {
        departmentServ.delete(departmentId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<Page<GetDepartmentResponse>> search(
            @RequestBody SearchDepartmentRequest request,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<GetDepartmentResponse> page = departmentServ.search(request, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{departmentId}/members")
    public ResponseEntity<List<GetDoctorResponse>> listMembers(@PathVariable UUID departmentId) {
        List<GetDoctorResponse> members = departmentServ.listMembers(departmentId);
        return ResponseEntity.ok(members);
    }

    @PutMapping("/{departmentId}/members/{doctorId}")
    public ResponseEntity<Void> addMember(
            @PathVariable UUID departmentId,
            @PathVariable UUID doctorId
    ) {
        departmentServ.addDoctor(departmentId, doctorId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{departmentId}/members/{doctorId}")
    public ResponseEntity<Void> removeMember(
            @PathVariable UUID departmentId,
            @PathVariable UUID doctorId
    ) {
        departmentServ.removeDoctor(departmentId, doctorId);
        return ResponseEntity.noContent().build();
    }

}
