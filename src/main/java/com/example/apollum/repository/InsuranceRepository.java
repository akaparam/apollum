package com.example.apollum.repository;

import com.example.apollum.models.Insurance;
import com.example.apollum.models.dto.CountByInsuranceProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, UUID> {

    @Query
    Page<Insurance> findByProvider(String provider, Pageable pageable);

    @Query("select i.provider as provider, count(i) as count from Insurance i group by i.provider")
    List<CountByInsuranceProvider> countInsuranceByProvider();
}