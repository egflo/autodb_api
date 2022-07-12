package com.autodb_api.repositories;

import com.autodb_api.models.Body;
import com.autodb_api.models.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("LocationRepository")
public interface LocationRepository extends JpaRepository<Location, Integer> {

    Optional<Location> findById(Integer id);

    Page<Location> findAll (Pageable pageable);

    Optional<Location> findByPostcode(Integer postcode);


}