package com.example.apollum.service;

import com.example.apollum.models.Patient;
import com.example.apollum.models.contracts.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PatientService {
    CreatePatientResponse create(CreatePatientRequest request);
    GetPatientResponse get(UUID patientId);
    void update(UUID patientId, UpdatePatientRequest request);
    void delete(UUID patientId);

    Page<GetPatientResponse> search(SearchPatientRequest request, Pageable pageable);
    HasInsuranceResponse hasInsurance(UUID patientId);
    CreateInsuranceResponse addInsurance(UUID patientId, CreateInsuranceRequest request);
    void removeInsurance(UUID patientId);

    Page<GetAppointmentResponse> getAllAppointments(UUID patientId, Pageable pageable);

    static GetPatientResponse toResponse(Patient patient) {
        return new GetPatientResponse(
                patient.getId().toString(),
                patient.getName(),
                patient.getDob().toString(),
                patient.getGender().toString(),
                patient.getEmail(),
                patient.getBloodGroup().toString(),
                patient.getCreatedAt().toString(),
                patient.getModifiedAt().toString()
        );
    }
//    Page<GetPatientResponse> getAll(Pageable page);
//    Page<GetPatientResponse> searchByName(String name);
//    Page<GetPatientResponse> searchByEmail(String email);
}
