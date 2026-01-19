package com.example.apollum.models.contracts;

import jakarta.annotation.Nonnull;

import java.util.UUID;

public record CreateDepartmentRequest(
        String name,
        @Nonnull UUID headDoctorId
) {
}
