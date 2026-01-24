package com.example.apollum.service.implementation;

import com.example.apollum.models.Appointment;
import com.example.apollum.models.Insurance;
import com.example.apollum.models.Patient;
import com.example.apollum.models.contracts.*;
import com.example.apollum.models.error.AppException;
import com.example.apollum.models.error.MessageConstants;
import com.example.apollum.models.error.SysException;
import com.example.apollum.repository.AppointmentRepository;
import com.example.apollum.repository.EntitySpecification;
import com.example.apollum.repository.InsuranceRepository;
import com.example.apollum.repository.PatientRepository;
import com.example.apollum.service.AppointmentService;
import com.example.apollum.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private InsuranceRepository insuranceRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Override
    public CreatePatientResponse create(CreatePatientRequest request) {

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
        Optional<Patient> existingPatient = patientRepo.findByEmailIgnoreCase(request.email());

        if (existingPatient.isPresent()) {
            throw new AppException(
                    MessageConstants.EMAIL_ALREADY_EXISTS,
                    "Please try logging in instead");
        }

        if (!request.dob().isBefore(LocalDate.now())) {
            throw new AppException(
                    MessageConstants.DOB_MUST_BE_IN_PAST,
                    "Please specify a meaningful date. Duh!"
            );
        }

        Patient patient = new Patient();
        patient.setName(request.name());
        patient.setEmail(request.email());
        patient.setDob(request.dob());
        patient.setBloodGroup(request.bloodGroup());
        patient.setGender(request.gender());

        try {
            patientRepo.save(patient);
        } catch (Exception ex) {
            throw new SysException("Internal Server Error", ex.getMessage());
        }

        return new CreatePatientResponse(
                patient.getId().toString()
        );
    }

    @Override
    public GetPatientResponse get(UUID patientId) {
        Optional<Patient> patient = patientRepo.findById(patientId);

        if (patient.isPresent()) {
            return PatientService.toResponse(patient.get());
        }

        throw new EntityNotFoundException("Patient with id " + patientId + " not found");
    }

    @Override
    @Transactional
    public void update(UUID patientId, UpdatePatientRequest request) {
        Optional<Patient> patient = patientRepo.findById(patientId);

        if (patient.isEmpty()) {
            throw new EntityNotFoundException("Patient with patient id " + patientId + " not found");
        }

        if (request.name() != null && !request.name().isBlank()) {
            patient.get().setName(request.name());
        }

        if (request.email() != null && !request.email().isBlank()) {
            patient.get().setEmail(request.email());
        }

        if (request.dob() != null) {
            patient.get().setDob(request.dob());
        }

        if (request.gender().isPresent()) {
            patient.get().setGender(request.gender().get());
        }

        if (request.bloodGroup().isPresent()) {
            patient.get().setBloodGroup(request.bloodGroup().get());
        }

        try{
            patientRepo.saveAndFlush(patient.get());
        }
        catch(Exception ex) {
            throw new AppException("DB Error occurred!", ex.getMessage());
        }
    }

    @Override
    public void delete(UUID patientId) {
        patientRepo.deleteById(patientId);
    }

    @Override
    public Page<GetPatientResponse> search(SearchPatientRequest request, Pageable pageable) {

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Patient> query = cb.createQuery(Patient.class);
//
//        Root<Patient> root = query.from(Patient.class);
//
//        query.select(root);
//
//        TypedQuery<Patient> tq = em.createQuery(query);
//        List<Patient> patients = tq.getResultList();
//
//
//        return null;

        Specification<Patient> spec = Specification.where(EntitySpecification.baseCondition());

        if (request.nameContaining().isPresent()) {
            spec = spec.and(EntitySpecification.withNameContaining(request.nameContaining().get()));
        }

        if (request.emailContaining().isPresent()) {
            spec = spec.and(EntitySpecification.withEmailContaining(request.emailContaining().get()));
        }

        if (request.age().isPresent()) {
            if (request.age().get().greaterThan().isPresent()) {
                int age = request.age().get().greaterThan().get();
                spec = spec.and(EntitySpecification.withDobLessThan(LocalDate.now().minusYears(age)));
            }

            if (request.age().get().lessThan().isPresent()) {
                int age = request.age().get().lessThan().get();
                spec = spec.and(EntitySpecification.withDobGreaterThan(LocalDate.now().minusYears(age)));
            }
        }

        if (request.gender().isPresent()) {
            spec = spec.and(EntitySpecification.withGender(request.gender().get()));
        }

        if (request.bloodGroup().isPresent()) {
            spec = spec.and(EntitySpecification.withBloodGroup(request.bloodGroup().get()));
        }

        Page<Patient> patients = patientRepo.findAll(spec, pageable);

        return patients.map(PatientService::toResponse);
    }

    @Override
    public HasInsuranceResponse hasInsurance(UUID patientId) {
        Optional<String> insuranceId = patientRepo.hasInsurance(patientId);

        return insuranceId.map(s -> new HasInsuranceResponse(true, s)).orElseGet(() -> new HasInsuranceResponse(false, null));

    }

    @Override
    @Transactional
    public CreateInsuranceResponse addInsurance(UUID patientId, CreateInsuranceRequest request) {
        Optional<Patient> _patient = patientRepo.findById(patientId);

        if (_patient.isEmpty()) {
            throw new EntityNotFoundException("Patient with id " + patientId + " not found");
        }

        Patient patient = _patient.get();
        if (patient.getInsuranceId() != null) {
            throw new AppException(
                    MessageConstants.INSURANCE_ALREADY_ATTACHED_TO_PATIENT,
                    "Please remove the previous insurance with id " + patient.getInsuranceId() + "to proceed adding a new one");
        }

        Insurance newInsurance = new Insurance(request.policyNumber(), request.provider(), request.expiry());
        insuranceRepo.save(newInsurance);

        patient.setInsurance(newInsurance);
        patientRepo.save(patient);

        return new CreateInsuranceResponse(newInsurance.getId().toString());
    }

    @Override
    @Transactional
    public void removeInsurance(UUID patientId) {
        Optional<Patient> _patient = patientRepo.findById(patientId);

        if (_patient.isEmpty()) {
            throw new EntityNotFoundException("Patient with id " + patientId + " not found");
        }
        Patient patient = _patient.get();

        patient.setInsurance(null);

        patientRepo.save(patient);
    }

    public Page<GetAppointmentResponse> getAllAppointments(UUID patientId, Pageable pageable) {
        Page<Appointment> appointments = appointmentRepo.findByPatientId(patientId, pageable);

        return appointments.map(AppointmentService::toResponse);
    }
}
