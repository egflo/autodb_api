package com.autodb_api.repositories;

import com.autodb_api.models.Auto;
import com.autodb_api.models.Body;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("BodyRepository")
public interface BodyRepository extends JpaRepository<Body, Integer> {

    Optional<Body> findById(Integer id);

    Page<Body> findAll (Pageable pageable);

    Page<Body> findByBodyType(Integer id, Pageable pageable);

}