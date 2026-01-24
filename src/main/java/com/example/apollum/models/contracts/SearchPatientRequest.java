package com.example.apollum.models.contracts;

import com.example.apollum.models.type.BloodGroupType;
import com.example.apollum.models.type.GenderType;

import java.util.Optional;

public record SearchPatientRequest(
        Optional<String> nameContaining,
        Optional<String> emailContaining,
        Optional<PatientAgeRange> age,
        Optional<GenderType> gender,
        Optional<BloodGroupType> bloodGroup
) {}

