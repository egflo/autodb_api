package com.autodb_api.repositories;

import com.autodb_api.models.Body;
import com.autodb_api.models.Transmission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("TransmissionRepository")
public interface TransmissionRepository extends JpaRepository<Transmission, Integer> {

    Optional<Transmission> findById(Integer id);

    ArrayList<Transmission> findAll();
}