package com.example.apollum.bleh;

import com.example.apollum.models.Doctor;
import com.example.apollum.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class DoctorRepositoryTests {

    @Autowired
    private DoctorRepository doctorRepo;

    @Test
    void findByEmailContainingIgnoreCase() {

//        List<Doctor> doctors = doctorRepo.findByEmailContainingIgnoreCase("hoSpiTaL");
//
//        for (var i : doctors) {
//            System.out.println(i);
//        }
    }

    @Test
    void findByNameContainingIgnoreCase() {

//        List<Doctor> doctors = doctorRepo.findByNameContainingIgnoreCase("sH");
//
//        for (var i : doctors) {
//            System.out.println(i);
//        }
    }

    @Test
    void findAllDoctors() {
        Page<Doctor> doctors = doctorRepo.findAllDoctors(PageRequest.of(0, 4));

        for(var i : doctors) {
            System.out.println(i);
        }
    }
}
