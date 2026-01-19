package com.example.apollum.models.contracts;

import com.example.apollum.models.type.DoctorSpecializationType;

public record CreateDoctorRequest(
        String name,
        DoctorSpecializationType specialization,
        String email
) {
}
