package com.example.apollum.models.contracts;

import com.example.apollum.models.type.BloodGroupType;
import com.example.apollum.models.type.GenderType;

import java.time.LocalDate;
import java.util.Optional;

public record UpdatePatientRequest(
        String name,
        LocalDate dob, // Ensure "YYYY-MM-DD" format
        Optional<GenderType> gender, // Validate enum??
        String email, // Validate email
        Optional<BloodGroupType> bloodGroup // Validate enum??
) {
}
