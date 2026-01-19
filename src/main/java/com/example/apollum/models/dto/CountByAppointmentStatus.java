package com.example.apollum.models.dto;

import com.example.apollum.models.type.AppointmentStatusType;

public interface CountByAppointmentStatus {
    AppointmentStatusType getStatus();
    int getCount();
}
