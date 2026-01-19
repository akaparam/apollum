package com.example.apollum.models.contracts;

public record GetPatientResponse(
        String id,
        String name,
        String dob, // Humanified
        String gender,
        String email,
        String bloodGroup,
        String createdAt,
        String modifiedAt
) {
}
