package com.example.apollum.models.dto;

import com.example.apollum.models.type.GenderType;

public interface CountByGender {
    GenderType getGender();
    int getCount();
}
