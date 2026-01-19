package com.example.apollum.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "insurance",
        indexes = {
                @Index(name = "idx__insurance_policy_number", columnList = "policy_number"),
                @Index(name = "idx__insurance_provider", columnList = "provider")
        }
)
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Insurance extends BaseEntity {

    @Column(name = "policy_number", unique = true, length = 10, nullable = false)
    String policyNumber;

    @Column(name = "provider")
    String provider;

    @Column(name = "expiry", nullable = false)
    LocalDate expiry;

}