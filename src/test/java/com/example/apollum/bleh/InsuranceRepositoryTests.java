package com.example.apollum.bleh;

import com.example.apollum.models.Insurance;
import com.example.apollum.models.dto.CountByInsuranceProvider;
import com.example.apollum.repository.InsuranceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class InsuranceRepositoryTests {

    @Autowired
    private InsuranceRepository insuranceRepo;

    @Test
    public void testInsuranceRepository_findById() {
        Optional<Insurance> insurance = insuranceRepo.findById(UUID.fromString("5e7f91a3-d6f8-7182-c5e7-6f91b4d65f56"));

        System.out.println(insurance);
    }
    @Test
    public void testInsuranceRepository_countInsuranceByProvider() {
        List<CountByInsuranceProvider> lstCount = insuranceRepo.countInsuranceByProvider();

        for (CountByInsuranceProvider c : lstCount) {
            System.out.println(c.getProvider() + " : " + c.getCount());
        }
    }
}
