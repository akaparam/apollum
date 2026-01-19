package com.example.apollum.models.contracts;

public record SearchDoctorsRequest(
        String name,
        String email,
        String specialization
) {
}
