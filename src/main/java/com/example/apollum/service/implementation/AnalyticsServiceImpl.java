package com.example.apollum.service.implementation;

import com.example.apollum.models.Insurance;
import com.example.apollum.models.contracts.GetInsuranceResponse;
import com.example.apollum.models.dto.CountByBloodGroup;
import com.example.apollum.models.dto.CountByGender;
import com.example.apollum.models.dto.CountByInsuranceProvider;
import com.example.apollum.models.type.AppointmentStatusType;
import com.example.apollum.models.type.BloodGroupType;
import com.example.apollum.models.type.DoctorSpecializationType;
import com.example.apollum.models.type.GenderType;
import com.example.apollum.repository.InsuranceRepository;
import com.example.apollum.repository.PatientRepository;
import com.example.apollum.service.AnalyticsService;
import com.example.apollum.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private InsuranceRepository insuranceRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Override
    public Page<GetInsuranceResponse> listInsuranceByProvider(String provider, Pageable pageable) {
        Page<Insurance> insurance = insuranceRepo.findByProvider(provider, pageable);

        return insurance.map(InsuranceService::toResponse);
    }

    @Override
    public List<CountByInsuranceProvider> countInsuranceByProvider() {

        return insuranceRepo.countInsuranceByProvider();
    }

    @Override
    public List<CountByBloodGroup> countPatientsByBloodGroup() {
        return patientRepo.countPatientByBloodGroup();
    }

    @Override
    public List<CountByGender> countPatientsByGender() {
        return patientRepo.countPatientByGender();
    }

    @Override
    public GenderType[] registeredGenders() {
        return GenderType.values();
    }

    @Override
    public BloodGroupType[] registeredBloodGroups() {
        return BloodGroupType.values();
    }

    @Override
    public DoctorSpecializationType[] availableSpecialists() {
        return DoctorSpecializationType.values();
    }

    @Override
    public AppointmentStatusType[] appointmentStatuses() {
        return AppointmentStatusType.values();
    }
}
