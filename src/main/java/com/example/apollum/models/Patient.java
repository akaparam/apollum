package com.example.apollum.models;

import com.example.apollum.models.type.BloodGroupType;
import com.example.apollum.models.type.GenderType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "patients",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_patient_name_dob", columnNames = {"name", "dob"})
        },
        indexes = {
                @Index(name = "idx__patient_dob", columnList = "dob"),
                @Index(name = "idx__patient_email", columnList = "email"),
                @Index(name = "idx__patient_blood_group", columnList = "blood_group")
        }
)
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends BaseEntity {

    @Column(name = "name", nullable = false, length = 40)
    String name;

    @Column(name = "dob")
    LocalDate dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    GenderType gender;

    @Column(name = "email", unique = true)
    String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group")
    BloodGroupType bloodGroup;

    @Column(name = "insurance_id", insertable=false, updatable=false)
    UUID insuranceId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "insurance_id")
    @ToString.Exclude
    Insurance insurance;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    List<Appointment> appointments;
}