package com.example.apollum.service.implementation;

import com.example.apollum.models.Appointment;
import com.example.apollum.models.Department;
import com.example.apollum.models.Doctor;
import com.example.apollum.models.contracts.*;
import com.example.apollum.models.error.AppException;
import com.example.apollum.models.error.MessageConstants;
import com.example.apollum.models.error.SysException;
import com.example.apollum.repository.AppointmentRepository;
import com.example.apollum.repository.DepartmentRepository;
import com.example.apollum.repository.DoctorRepository;
import com.example.apollum.service.AppointmentService;
import com.example.apollum.service.DepartmentService;
import com.example.apollum.service.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Override
    public CreateDoctorResponse create(CreateDoctorRequest request) {
        if (request.email() == null || request.email().isBlank()) {
            throw new AppException(
                    MessageConstants.EMAIL_MUST_NOT_BE_BLANK,
                    "Please specify a valid email to proceed");
        }

        if (request.name() == null || request.name().isBlank()) {
            throw new AppException(
                    MessageConstants.NAME_MUST_NOT_BE_BLANK,
                    "Please specify a valid name to proceed");
        }
        Optional<Doctor> existingDoctor = doctorRepo.findByEmailIgnoreCase(request.email());

        if (existingDoctor.isPresent()) {
            throw new AppException(
                    MessageConstants.EMAIL_ALREADY_EXISTS,
                    "Please try logging in instead");
        }

        Doctor doctor = new Doctor();
        doctor.setName(request.name());
        doctor.setEmail(request.email());
        doctor.setSpecialization(request.specialization());

        try {
            doctorRepo.save(doctor);
        } catch (Exception ex) {
            throw new SysException("Internal Server Error", ex.getMessage());
        }

        return new CreateDoctorResponse(
                doctor.getId().toString()
        );
    }

    @Override
    public GetDoctorResponse get(UUID doctorId) {
        Optional<Doctor> doctor = doctorRepo.findById(doctorId);

        if (doctor.isPresent()) {
            return DoctorService.toResponse(doctor.get());
        }

        throw new EntityNotFoundException("Doctor with id " + doctorId + " not found");
    }

    @Override
    public void updateSpec(UUID doctorId, UpdateDoctorSpecializationRequest request) {
        Optional<Doctor> doctor = doctorRepo.findById(doctorId);

        if (doctor.isEmpty()) {
            throw new EntityNotFoundException("Doctor with doctor id " + doctorId + " not found");
        }

        if (request.specialization() != null) {
            doctor.get().setSpecialization(request.specialization());
        }

        try{
            doctorRepo.saveAndFlush(doctor.get());
        }
        catch(Exception ex) {
            throw new AppException("DB Error occurred!", ex.getMessage());
        }
    }

    @Override
    public void delete(UUID doctorId) {
        doctorRepo.deleteById(doctorId);
    }

    @Override
    public Page<GetDoctorResponse> getAllDoctors(SearchDoctorsRequest request, Pageable pageable) {
        throw new NotImplementedException();
    }

    @Override
    public List<GetDepartmentResponse> listEnrolledDepartments(UUID doctorId) {
        List<Department> departments = departmentRepo.findAllByDoctorId(doctorId);
        return departments.stream().map(DepartmentService::toResponse).toList();
    }

    @Override
    public Page<GetAppointmentResponse> getAllAppointments(UUID doctorId, Pageable pageable) {
        Page<Appointment> appointments = appointmentRepo.findByDoctorId(doctorId, pageable);

        return appointments.map(AppointmentService::toResponse);
    }
}
