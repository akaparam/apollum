package com.example.apollum.models.contracts;

import com.example.apollum.models.type.BloodGroupType;
import com.example.apollum.models.type.GenderType;

import java.util.Optional;

public record SearchPatientRequest(
        String nameContaining,
        String emailContaining,
        Optional<AgeRange> age,
        Optional<GenderType> gender,
        Optional<BloodGroupType> bloodGroup
) {}

record AgeRange(
        Optional<Integer> greaterThan,
        Optional<Integer> lessThan
) {}