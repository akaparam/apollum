package com.example.apollum.service;

import com.example.apollum.models.contracts.GetInsuranceResponse;
import com.example.apollum.models.dto.CountByBloodGroup;
import com.example.apollum.models.dto.CountByGender;
import com.example.apollum.models.dto.CountByInsuranceProvider;
import com.example.apollum.models.type.AppointmentStatusType;
import com.example.apollum.models.type.BloodGroupType;
import com.example.apollum.models.type.DoctorSpecializationType;
import com.example.apollum.models.type.GenderType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnalyticsService {
    Page<GetInsuranceResponse> listInsuranceByProvider(String provider, Pageable pageable);
    List<CountByInsuranceProvider> countInsuranceByProvider();

    List<CountByBloodGroup> countPatientsByBloodGroup();
    List<CountByGender> countPatientsByGender();

    // For UI mapping
    GenderType[] registeredGenders();
    BloodGroupType[] registeredBloodGroups();
    DoctorSpecializationType[] availableSpecialists();
    AppointmentStatusType[] appointmentStatuses();

}
