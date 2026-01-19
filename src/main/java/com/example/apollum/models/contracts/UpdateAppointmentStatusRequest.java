package com.example.apollum.models.contracts;

import com.example.apollum.models.type.AppointmentStatusType;

public record UpdateAppointmentStatusRequest(
        AppointmentStatusType status
) {
}
