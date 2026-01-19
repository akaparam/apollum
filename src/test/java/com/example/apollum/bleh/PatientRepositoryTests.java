package com.example.apollum.bleh;

import com.example.apollum.models.dto.CountByBloodGroup;
import com.example.apollum.models.dto.CountByGender;
import com.example.apollum.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class PatientRepositoryTests {

    @Autowired
    private PatientRepository patientRepo;

    @Test
    void testPatientRepository_findAllPatients() {
//        Page<Patient> patients = patientRepo.findAllPatients(PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "name")));
//
//        for (Patient p : patients.getContent()) {
//            System.out.println(p);
//        }
    }

    @Test
    void testPatientRepository_hasInsurance() {
        // d406e4da-9d70-4e21-bd41-5d8fb0214a04 => NULL
        // e517f5eb-ae81-4f32-ce52-6e90c1325b05 => YAY!
        Optional<String> insurance = patientRepo.hasInsurance(UUID.fromString("e517f5eb-ae81-4f32-ce52-6e90c1325b05"));
        if (insurance.isEmpty()) {
            System.out.println("NULL");
        } else {
            System.out.println(insurance.get());
        }
    }

    @Test
    void findByEmailContainingIgnoreCase_happyPath() {
//        List<Patient> results = patientRepo.findByEmailContainingIgnoreCase("EXAMPLE");
//        for (var i : results) {
//            System.out.println(i);
//        }
    }

    @Test
    void findByNameContainingIgnoreCase_happyPath() {
//        List<Patient> results = patientRepo.findByNameContainingIgnoreCase("sh");
//
//        for (var i : results) {
//            System.out.println(i);
//        }
    }

    @Test
    void countPatientByBloodGroup() {
        List<CountByBloodGroup> results = patientRepo.countPatientByBloodGroup();

        for (var i : results) {
            System.out.println(i.getBloodGroup() + " : " + i.getCount());
        }
    }

    @Test
    void countPatientByGender() {
        List<CountByGender> results = patientRepo.countPatientByGender();

        for (var i : results) {
            System.out.println(i.getGender() + " : " + i.getCount());
        }
    }

    @Test
    void findByDobBetween() {
        LocalDate startDate = LocalDate.of(1990,07,03);
        LocalDate endDate = LocalDate.now();
//        List<Patient> results = patientRepo.findByDobBetween(startDate, endDate);
//
//        for (var i : results) {
//            System.out.println(i);
//        }
    }

    @Test
    void findByDobBetweenAndGender() {
        LocalDate startDate = LocalDate.of(1990,07,03);
        LocalDate endDate = LocalDate.now();
//        List<Patient> results = patientRepo.findByDobBetweenAndGender(startDate, endDate, GenderType.FEMALE);
//
//        for (var i : results) {
//            System.out.println(i);
//        }
    }

    @Test
    void findByDobBetweenAndGenderAndBloodGroup() {
        LocalDate startDate = LocalDate.of(1990,07,03);
        LocalDate endDate = LocalDate.now();
//        List<Patient> results = patientRepo.findByDobBetweenAndGenderAndBloodGroup(startDate, endDate, GenderType.FEMALE, BloodGroupType.A_NEGATIVE);
//
//        for (var i : results) {
//            System.out.println(i);
//        }
    }

    @Test
    void deletePatientById() {
        patientRepo.deleteById(UUID.fromString("a1d3b1a7-6a4d-4b9e-8a1e-2a5c8f9d1e01"));
    }
}
