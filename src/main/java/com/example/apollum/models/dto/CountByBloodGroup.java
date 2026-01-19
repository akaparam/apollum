package com.example.apollum.models.dto;

import com.example.apollum.models.type.BloodGroupType;

public interface CountByBloodGroup{
    BloodGroupType getBloodGroup();
    int getCount();
}
