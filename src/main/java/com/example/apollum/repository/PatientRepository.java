package com.example.apollum.repository;

import com.example.apollum.models.Patient;
import com.example.apollum.models.dto.CountByBloodGroup;
import com.example.apollum.models.dto.CountByGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    @Query("SELECT p.insuranceId FROM Patient p WHERE p.id = :patientId")
    Optional<String> hasInsurance(@Param("patientId") UUID patientId);

    @Query
    Optional<Patient> findByEmailIgnoreCase(String email);

    // Endpoints for analytics
    @Query("SELECT p.bloodGroup AS bloodGroup, count(p) AS count FROM Patient p GROUP BY p.bloodGroup")
    List<CountByBloodGroup> countPatientByBloodGroup();

    @Query("SELECT p.gender AS gender, count(p) AS count FROM Patient p GROUP BY p.gender")
    List<CountByGender> countPatientByGender();

}
