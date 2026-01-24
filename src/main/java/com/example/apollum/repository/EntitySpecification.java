package com.example.apollum.repository;

import com.example.apollum.models.Appointment;
import com.example.apollum.models.Doctor;
import com.example.apollum.models.Patient;
import com.example.apollum.models.type.BloodGroupType;
import com.example.apollum.models.type.GenderType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class EntitySpecification {

    private EntitySpecification() {}

    public static <T> Specification<T> baseCondition() {
        return (_, _, cb) -> cb.conjunction();
    }
    public static <T> Specification<T> withNameContaining(String str) {
        return (root, _, cb)
                -> cb.like(cb.lower(root.get("name")),"%" + str.toLowerCase() + "%");
    }

    public static <T> Specification<T> withEmailContaining(String str) {
        return (root, _, cb)
                -> cb.like(cb.lower(root.get("email")),"%" + str.toLowerCase() + "%");
    }

    public static Specification<Patient> withDobLessThan(LocalDate dob) { // age greater than
        return (root, _, cb)
                -> cb.lessThan(root.get("dob"), dob);
    }

    public static Specification<Patient> withDobGreaterThan(LocalDate dob) { // age greater than
        return (root, _, cb)
                -> cb.greaterThan(root.get("dob"), dob);
    }

    public static Specification<Patient> withGender(GenderType gender) { // age greater than
        return (root, _, cb)
                -> cb.equal(root.get("gender"), gender);
    }

    public static Specification<Patient> withBloodGroup(BloodGroupType bloodGroup) { // age greater than
        return (root, _, cb)
                -> cb.equal(root.get("bloodGroup"), bloodGroup);
    }

    public static Specification<Doctor> withSpecialization(String specialization) {
        return (root, _, cb) ->
                cb.equal(root.get("specialization"), specialization);
    }

    public static Specification<Appointment> withTitleContaining(String str) {
        return (root, _, cb) ->
                cb.like(
                        cb.lower(root.get("title")),
                        "%" + str.toLowerCase() + "%"
                );
    }

    public static Specification<Appointment> withStatus(String status) {
        return (root, _, cb) ->
                cb.equal(root.get("status"), status);
    }

    public static Specification<Appointment> startAfter(LocalDateTime t) {
        return (root, _, cb) ->
                cb.greaterThan(root.get("startTime"), t);
    }

    public static Specification<Appointment> startBefore(LocalDateTime t) {
        return (root, _, cb) ->
                cb.lessThan(root.get("startTime"), t);
    }

    public static Specification<Appointment> endAfter(LocalDateTime t) {
        return (root, _, cb) ->
                cb.greaterThan(root.get("endTime"), t);
    }

    public static Specification<Appointment> endBefore(LocalDateTime t) {
        return (root, _, cb) ->
                cb.lessThan(root.get("endTime"), t);
    }

}
