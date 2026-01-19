package com.example.apollum.repository;

import com.example.apollum.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    @Query
    Optional<Doctor> findByEmailIgnoreCase(String email);

    @Query("SELECT d FROM Doctor d")
    Page<Doctor> findAllDoctors(Pageable page);
}