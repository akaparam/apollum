package com.example.apollum.models.contracts;

import java.time.LocalDateTime;

public record UpdateAppointmentSlotRequest(
        LocalDateTime startTime, // Ensure YYYY-MM-DD format
        LocalDateTime endTime // Ensure YYYY-MM-DD format
) {
}
