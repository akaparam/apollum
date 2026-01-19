package com.example.apollum.service;

import com.example.apollum.models.Insurance;
import com.example.apollum.models.contracts.GetInsuranceResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface InsuranceService {

    GetInsuranceResponse getInsuranceById(UUID id);

    static GetInsuranceResponse toResponse(Insurance insurance) {
        return new GetInsuranceResponse(
                insurance.getId().toString(),
                insurance.getPolicyNumber(),
                insurance.getProvider(),
                insurance.getExpiry().toString(),
                insurance.getCreatedAt().toString(),
                insurance.getModifiedAt().toString()
        );
    }
}
