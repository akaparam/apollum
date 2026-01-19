package com.example.apollum.models;

import com.example.apollum.models.type.DoctorSpecializationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "doctors",
        indexes = {
                @Index(name = "idx__doctor_specialization", columnList = "specialization"),
                @Index(name = "idx__doctor_email", columnList = "email")
        }
)
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Doctor extends BaseEntity {

    @Column(name = "name")
    String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialization")
    DoctorSpecializationType specialization;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @OneToMany(mappedBy = "doctorId", fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Appointment> appointments;

    @ManyToMany(mappedBy = "doctors", fetch = FetchType.LAZY)
    @ToString.Exclude
    Set<Department> departments = new HashSet<>();
}
