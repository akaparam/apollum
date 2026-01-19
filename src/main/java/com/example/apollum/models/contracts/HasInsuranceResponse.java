package com.example.apollum.models.contracts;

public record HasInsuranceResponse(
        boolean isPatientInsured,
        String insuranceId
) {
}
