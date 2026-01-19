package com.example.apollum.controller;



import com.example.apollum.models.contracts.GetInsuranceResponse;
import com.example.apollum.models.dto.CountByBloodGroup;
import com.example.apollum.models.dto.CountByGender;
import com.example.apollum.models.dto.CountByInsuranceProvider;
import com.example.apollum.models.type.AppointmentStatusType;
import com.example.apollum.models.type.BloodGroupType;
import com.example.apollum.models.type.DoctorSpecializationType;
import com.example.apollum.models.type.GenderType;
import com.example.apollum.service.AnalyticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/analytics", produces = "application/json")
@Tag(name = "Analytics", description = "Endpoints for fetching patient based analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsServ;

    @GetMapping("/listInsuranceByProvider")
    public ResponseEntity<Page<GetInsuranceResponse>> listInsuranceByProvider(
            @RequestParam(name = "provider") String provider,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<GetInsuranceResponse> page = analyticsServ.listInsuranceByProvider(provider, pageable);
        return ResponseEntity.ok(page);
    }


    @GetMapping("/countInsuranceByProvider")
    public ResponseEntity<List<CountByInsuranceProvider>> countInsuranceByProvider() {
        return ResponseEntity.ok(analyticsServ.countInsuranceByProvider());
    }

    @GetMapping("/countPatientsByBloodGroup")
    public ResponseEntity<List<CountByBloodGroup>> countPatientsByBloodGroup() {
        return ResponseEntity.ok(analyticsServ.countPatientsByBloodGroup());
    }

    @GetMapping("/countPatientsByGender")
    public ResponseEntity<List<CountByGender>> countPatientsByGender() {
        return ResponseEntity.ok(analyticsServ.countPatientsByGender());
    }

    @GetMapping("/genders")
    public ResponseEntity<GenderType[]> registeredGenders() {
        return ResponseEntity.ok(analyticsServ.registeredGenders());
    }

    @GetMapping("/patientBloodGroups")
    public ResponseEntity<BloodGroupType[]> registeredBloodGroups() {
        return ResponseEntity.ok(analyticsServ.registeredBloodGroups());
    }

    @GetMapping("/doctorSpecialists")
    public ResponseEntity<DoctorSpecializationType[]> doctorSpecialists() {
        return ResponseEntity.ok(analyticsServ.availableSpecialists());
    }

    @GetMapping("/appointmentStatuses")
    public ResponseEntity<AppointmentStatusType[]> appointmentStatuses() {
        return ResponseEntity.ok(analyticsServ.appointmentStatuses());
    }

}
