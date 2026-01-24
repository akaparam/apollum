package com.example.apollum.models.contracts;

import java.util.Optional;

public record PatientAgeRange(
        Optional<Integer> greaterThan,
        Optional<Integer> lessThan
) {}
