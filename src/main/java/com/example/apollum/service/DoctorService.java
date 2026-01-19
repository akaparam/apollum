package com.example.apollum.service;

import com.example.apollum.models.Doctor;
import com.example.apollum.models.contracts.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    CreateDoctorResponse create(CreateDoctorRequest request);
    GetDoctorResponse get(UUID doctorId);
    void updateSpec(UUID doctorId, UpdateDoctorSpecializationRequest request);
    void delete(UUID doctorId);

    Page<GetDoctorResponse> getAllDoctors(SearchDoctorsRequest request, Pageable pageable);
    List<GetDepartmentResponse> listEnrolledDepartments(UUID doctorId);

    Page<GetAppointmentResponse> getAllAppointments(UUID doctorId, Pageable pageable);

    static GetDoctorResponse toResponse(Doctor doctor) {
        return new GetDoctorResponse(
                doctor.getId().toString(),
                doctor.getName(),
                doctor.getSpecialization().toString(),
                doctor.getEmail(),
                doctor.getCreatedAt().toString(),
                doctor.getModifiedAt().toString()
        );
    }
}
