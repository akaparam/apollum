package com.example.apollum.models.contracts;

import jakarta.annotation.Nonnull;

import java.time.LocalDate;

public record CreateInsuranceRequest(
        @Nonnull String policyNumber,
        @Nonnull String provider,
        @Nonnull LocalDate expiry
) {
}
