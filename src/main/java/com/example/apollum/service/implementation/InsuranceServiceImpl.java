package com.example.apollum.service.implementation;

import com.example.apollum.models.Insurance;
import com.example.apollum.models.contracts.GetInsuranceResponse;
import com.example.apollum.repository.InsuranceRepository;
import com.example.apollum.service.InsuranceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepo;

    public GetInsuranceResponse getInsuranceById(UUID insuranceId) {
        Optional<Insurance> insurance = insuranceRepo.findById(insuranceId);
        if (insurance.isPresent()) {
            return InsuranceService.toResponse(insurance.get());
        }

        throw new EntityNotFoundException("Insurance with " + insuranceId + " not found");
    }
}
