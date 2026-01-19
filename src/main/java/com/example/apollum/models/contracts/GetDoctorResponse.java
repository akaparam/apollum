package com.example.apollum.models.contracts;

public record GetDoctorResponse(
        String id,
        String name,
        String specialization,
        String email,
        String createdAt,
        String modifiedAt
) {
}
