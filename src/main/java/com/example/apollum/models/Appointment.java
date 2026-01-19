package com.example.apollum.models;


import com.example.apollum.models.type.AppointmentStatusType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "appointments",
        indexes = {
                @Index(name = "idx__appointment_start_time__appointment_end_time", columnList = "start_time, end_time"),
                @Index(name = "idx__appointment_status", columnList = "status")
        }
)
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Appointment extends BaseEntity {

    @Column(name = "start_time", nullable = false)
    LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    LocalDateTime endTime;

    @Column(name = "reason")
    String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    AppointmentStatusType status = AppointmentStatusType.SCHEDULED;

    @Column(name = "doctor_id", insertable = false, updatable = false)
    UUID doctorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @ToString.Exclude
    Doctor doctor;

    @Column(name = "patient_id", insertable = false, updatable = false)
    UUID patientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @ToString.Exclude
    Patient patient;

}

