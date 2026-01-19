package com.example.apollum.models.contracts;

public record GetAppointmentResponse(
        String id,
        String startTime,
        String endTime,
        String title,
        String status,
        String doctorId,
        String patientId,
        String createdAt,
        String modifiedAt
) {
}
