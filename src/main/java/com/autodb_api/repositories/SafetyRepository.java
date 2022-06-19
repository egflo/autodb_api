package com.autodb_api.repositories;

import com.autodb_api.models.Body;
import com.autodb_api.models.Safety;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("SafetyRepository")
public interface SafetyRepository extends JpaRepository<Safety, Integer> {

    Optional<Safety> findById(Integer id);

    Page<Safety> findAll (Pageable pageable);
    Optional<Safety> findByVehicleId(int vehicleId);
}