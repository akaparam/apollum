package com.example.apollum.service.implementation;

import com.example.apollum.models.Department;
import com.example.apollum.models.Doctor;
import com.example.apollum.models.contracts.*;
import com.example.apollum.models.error.AppException;
import com.example.apollum.models.error.MessageConstants;
import com.example.apollum.models.error.SysException;
import com.example.apollum.repository.DepartmentRepository;
import com.example.apollum.repository.DoctorRepository;
import com.example.apollum.repository.EntitySpecification;
import com.example.apollum.service.DepartmentService;
import com.example.apollum.service.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private DoctorRepository doctorRepo;

    @Override
    public CreateDepartmentResponse create(CreateDepartmentRequest request) {

        if (request.name() == null || request.name().isBlank()) {
            throw new AppException(
                    MessageConstants.NAME_MUST_NOT_BE_BLANK,
                    "Please specify a valid name to proceed");
        }

        Optional<Doctor> _doctor = doctorRepo.findById(request.headDoctorId());

        if (_doctor.isEmpty()) {
            throw new EntityNotFoundException("Doctor with doctor id " + request.headDoctorId() + " not found");
        }

        Doctor doctor = _doctor.get();

        Department department = new Department();
        department.setName(request.name());
        department.setHeadDoctor(doctor);

        try {
            departmentRepo.save(department);
        } catch (Exception ex) {
            throw new SysException("Internal Server Error", ex.getMessage());
        }

        return new CreateDepartmentResponse(
                department.getId().toString()
        );
    }

    @Override
    public GetDepartmentResponse get(UUID departmentId) {
        Optional<Department> department = departmentRepo.findById(departmentId);

        if (department.isPresent()) {
            return DepartmentService.toResponse(department.get());
        }

        throw new EntityNotFoundException("Department with id " + departmentId + " not found");
    }

    @Override
    @Transactional
    public void updateHeadDoctor(UUID departmentId, UpdateDepartmentHeadDoctorRequest request) {
        Optional<Department> _department = departmentRepo.findById(departmentId);
        if (_department.isEmpty()) {
            throw new EntityNotFoundException("Department with department id " + departmentId + " not found");
        }

        Optional<Doctor> _headDoctor = doctorRepo.findById(request.headDoctorId());
        if (_headDoctor.isEmpty()) {
            throw new EntityNotFoundException("Doctor with doctor id " + request.headDoctorId() + " not found");
        }

        _department.get().setHeadDoctor(_headDoctor.get());

        departmentRepo.save(_department.get());
    }

    @Override
    public void delete(UUID departmentId) {
        departmentRepo.deleteById(departmentId);
    }

    @Override
    public Page<GetDepartmentResponse> search(SearchDepartmentRequest request, Pageable pageable) {
        Specification<Department> query = Specification.where(EntitySpecification.baseCondition());

        if (request.name() != null && !request.name().isBlank()) {
            query = query.and(EntitySpecification.withNameContaining(request.name()));
        }

        Page<Department> departments = departmentRepo.findAll(query, pageable);

        return departments.map(DepartmentService::toResponse);
    }

    @Override
    public List<GetDoctorResponse> listMembers(UUID departmentId) {
        List<Doctor> members = departmentRepo.findAllMembersByDepartment(departmentId);

        return members.stream().map(DoctorService::toResponse).toList();
    }

    @Override
    @Transactional
    public void addDoctor(UUID departmentId, UUID doctorId) {
        Optional<Department> _department = departmentRepo.findById(departmentId);
        if (_department.isEmpty()) {
            throw new EntityNotFoundException("Department with department id " + departmentId + " not found");
        }

        Optional<Doctor> _doctor = doctorRepo.findById(doctorId);
        if (_doctor.isEmpty()) {
            throw new EntityNotFoundException("Doctor with doctor id " + doctorId + " not found");
        }

        Set<Doctor> existingMembers = _department.get().getDoctors();
        existingMembers.add(_doctor.get());

        _department.get().setDoctors(existingMembers);
        departmentRepo.save(_department.get());
    }

    @Override
    public void removeDoctor(UUID departmentId, UUID doctorId) {
        Optional<Department> _department = departmentRepo.findById(departmentId);
        if (_department.isEmpty()) {
            throw new EntityNotFoundException("Department with department id " + departmentId + " not found");
        }

        Optional<Doctor> _doctor = doctorRepo.findById(doctorId);
        if (_doctor.isEmpty()) {
            throw new EntityNotFoundException("Doctor with doctor id " + doctorId + " not found");
        }

        Set<Doctor> existingMembers = _department.get().getDoctors();
        existingMembers.remove(_doctor.get());

        _department.get().setDoctors(existingMembers);
        departmentRepo.save(_department.get());
    }
}
