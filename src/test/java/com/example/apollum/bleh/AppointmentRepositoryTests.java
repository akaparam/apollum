package com.example.apollum.bleh;

import com.example.apollum.models.Appointment;
import com.example.apollum.models.dto.CountByAppointmentStatus;
import com.example.apollum.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class AppointmentRepositoryTests {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Test
    void findAllAppointments() {

//        Page<Appointment> appointments = appointmentRepo.getAllAppointments(PageRequest.of(0,4));

//        for (var i : appointments) {
//            System.out.println(i);
//        }
    }

    @Test
    void findByPatientId() {
        Page<Appointment> appointments = appointmentRepo.findByPatientId(UUID.fromString("b2e4c2b8-7b5e-4c0f-9b2f-3b6d9e0f2e02"), PageRequest.of(0,4));

        for (var i : appointments) {
            System.out.println(i);
        }
    }

    @Test
    void findByDoctorId() {
        Page<Appointment> appointments = appointmentRepo.findByDoctorId(UUID.fromString("d4f5a6b7-c8d9-4aeb-a456-7890123def44"), PageRequest.of(0,4));

        for (var i : appointments) {
            System.out.println(i);
        }
    }

    @Test
    void countAppointmentByStatus() {
        List<CountByAppointmentStatus> lstStatus = appointmentRepo.countAppointmentByStatus();

        for (var i : lstStatus) {
            System.out.println(i.getStatus() + " : " + i.getCount());
        }
    }

    @Test
    void findByReasonContainingIgnoreCase() {
//        Page<Appointment> appointments = appointmentRepo.findByReasonContainingIgnoreCase("gYn", PageRequest.of(0,4, Sort.Direction.ASC, "reason"));

//        for(var i : appointments) {
//            System.out.println(i);
//        }
    }
}
