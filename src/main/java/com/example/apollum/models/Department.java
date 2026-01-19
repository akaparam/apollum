package com.example.apollum.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "departments",
        indexes = {
                @Index(name = "idx__head_doctor_id", columnList = "head_doctor_id")
        }
)
public class Department extends BaseEntity {

    @Column(name = "name")
    String name;

    @Column(name = "head_doctor_id", insertable = false, updatable = false)
    UUID headDoctorId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "head_doctor_id")
    @ToString.Exclude
    Doctor headDoctor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "department_doctor",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    @ToString.Exclude
    Set<Doctor> doctors = new HashSet<>();

}
