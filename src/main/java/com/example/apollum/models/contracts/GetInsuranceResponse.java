package com.example.apollum.models.contracts;

public record GetInsuranceResponse(
        String id,
        String policyNumber,
        String provider,
        String expiry, // Humanify the local date object
        String createdAt,
        String modifiedAt
) {
}
