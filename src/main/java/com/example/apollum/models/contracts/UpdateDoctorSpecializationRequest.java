package com.example.apollum.models.contracts;

import com.example.apollum.models.type.DoctorSpecializationType;

public record UpdateDoctorSpecializationRequest(
        DoctorSpecializationType specialization
) {
}
