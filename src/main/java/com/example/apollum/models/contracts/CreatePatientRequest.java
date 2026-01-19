package com.example.apollum.models.contracts;

import com.example.apollum.models.type.BloodGroupType;
import com.example.apollum.models.type.GenderType;

import java.time.LocalDate;

public record CreatePatientRequest(
        String name,
        LocalDate dob,
        GenderType gender,
        String email,
        BloodGroupType bloodGroup
) {
}
