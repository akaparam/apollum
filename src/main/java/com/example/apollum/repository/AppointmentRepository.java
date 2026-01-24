package com.example.apollum.repository;

import com.example.apollum.models.Appointment;
import com.example.apollum.models.dto.CountByAppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends
        JpaRepository<Appointment, UUID>,
        JpaSpecificationExecutor<Appointment>
{

    @Query
    Page<Appointment> findByDoctorId(UUID doctorId, Pageable page);

    @Query
    Page<Appointment> findByPatientId(UUID patientId, Pageable page);

    @Query("SELECT a.status AS status, COUNT(a) AS count FROM Appointment a GROUP BY a.status")
    List<CountByAppointmentStatus> countAppointmentByStatus();
}