package com.example.apollum.service;

import com.example.apollum.models.Department;
import com.example.apollum.models.contracts.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    CreateDepartmentResponse create(CreateDepartmentRequest request);
    GetDepartmentResponse get(UUID departmentId);
    void updateHeadDoctor(UUID departmentId, UpdateDepartmentHeadDoctorRequest request);
    void delete(UUID departmentId);

    Page<GetDepartmentResponse> search(SearchDepartmentRequest request, Pageable pageable);

    List<GetDoctorResponse> listMembers(UUID departmentId);
    void addDoctor(UUID departmentId, UUID doctorId);
    void removeDoctor(UUID departmentId, UUID doctorId);

    static GetDepartmentResponse toResponse(Department department) {
        return new GetDepartmentResponse(
                department.getId().toString(),
                department.getName(),
                department.getHeadDoctorId().toString(),
                department.getCreatedAt().toString(),
                department.getModifiedAt().toString()
        );
    }
}
