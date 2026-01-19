package com.example.apollum.models.contracts;

public record SearchAppointmentRequest(
        String title,
        String status,
        AppointmentSlot slot
) {
}

record AppointmentSlot(
        String startTimeAfter,
        String startTimeBefore,
        String endTimeAfter,
        String endTimeBefore
) {}