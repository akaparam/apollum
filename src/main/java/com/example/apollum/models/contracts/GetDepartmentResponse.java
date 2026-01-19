package com.example.apollum.models.contracts;

public record GetDepartmentResponse(
        String id,
        String name,
        String headDoctorId,
        String createdAt,
        String modifiedAt
) {
}
