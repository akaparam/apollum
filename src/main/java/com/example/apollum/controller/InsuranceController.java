package com.example.apollum.controller;

import com.example.apollum.models.contracts.GetInsuranceResponse;
import com.example.apollum.service.InsuranceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping( value = "/api/v1/insurance", produces = "application/json")
@Tag(name = "Insurance", description = "Fetch insurance details")

public class InsuranceController {

    @Autowired
    private InsuranceService insuranceServ;

    @GetMapping("{insuranceId}")
    public ResponseEntity<GetInsuranceResponse> getById(@PathVariable UUID insuranceId) {
        GetInsuranceResponse response = insuranceServ.getInsuranceById(insuranceId);

        return ResponseEntity.ok(response);
    }
}
