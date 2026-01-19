package com.example.apollum.service;

import com.example.apollum.models.Appointment;
import com.example.apollum.models.contracts.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AppointmentService {
    CreateAppointmentResponse create(CreateAppointmentRequest request);
    GetAppointmentResponse get(UUID appointmentId);
    void updateSlot(UUID appointmentId, UpdateAppointmentSlotRequest request);
    void updateStatus(UUID appointmentId, UpdateAppointmentStatusRequest request);
    void delete(UUID appointmentId);

    // reason / title (change reason to title??)
    Page<GetAppointmentResponse> search(SearchAppointmentRequest request, Pageable pageable);

    static GetAppointmentResponse toResponse(Appointment appointment) {
        return new GetAppointmentResponse(
                appointment.getId().toString(),
                appointment.getStartTime().toString(),
                appointment.getEndTime().toString(),
                appointment.getReason(),
                appointment.getStatus().toString(),
                appointment.getDoctorId().toString(),
                appointment.getPatientId().toString(),
                appointment.getCreatedAt().toString(),
                appointment.getModifiedAt().toString()
        );
    }

}
