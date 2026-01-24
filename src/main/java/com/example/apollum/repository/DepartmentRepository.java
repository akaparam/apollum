package com.example.apollum.repository;

import com.example.apollum.models.Department;
import com.example.apollum.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DepartmentRepository extends
        JpaRepository<Department, UUID>,
        JpaSpecificationExecutor<Department>
{

    @Query("SELECT d FROM Department d JOIN d.doctors doc WHERE doc.id = :doctorId")
    List<Department> findAllByDoctorId(UUID doctorId);

    @Query("SELECT d FROM Doctor d JOIN d.departments dept WHERE dept.id = :departmentId")
    List<Doctor> findAllMembersByDepartment(UUID departmentId);
}