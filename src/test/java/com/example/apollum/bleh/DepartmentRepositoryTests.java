package com.example.apollum.bleh;

import com.example.apollum.models.Department;
import com.example.apollum.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class DepartmentRepositoryTests {

    @Autowired
    private DepartmentRepository departmentRepo;

    @Test
    void getAllDepartments() {
//        Page<Department> departments = departmentRepo.getAllDepartments(PageRequest.of(0,4));
//
//        for (var i : departments) {
//            System.out.println(i);
//        }
    }

    @Test
    void getById() {
        Optional<Department> dept = departmentRepo.findById(UUID.fromString("33333333-cccc-4b33-9333-ccccccccccc3"));
        System.out.println(dept);
    }
}