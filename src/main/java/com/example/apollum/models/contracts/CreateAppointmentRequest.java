package com.example.apollum.models.contracts;

import jakarta.annotation.Nonnull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateAppointmentRequest(
        String title,
        @Nonnull LocalDateTime startTime, // Ensure YYYY-MM-DD format
        @Nonnull LocalDateTime endTime, // Ensure YYYY-MM-DD format
        // String status, // No need! By default, this should be scheduled
        @Nonnull UUID doctorId,
        @Nonnull UUID patientId
) {
}
