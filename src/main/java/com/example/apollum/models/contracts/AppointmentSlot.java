package com.example.apollum.models.contracts;

public record AppointmentSlot(
        String startTimeAfter,
        String startTimeBefore,
        String endTimeAfter,
        String endTimeBefore
) {}
