package com.autodb_api.repositories;

import com.autodb_api.models.BodyType;
import com.autodb_api.models.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("FuelRepository")
public interface FuelRepository extends JpaRepository<Fuel, Integer> {

    Optional<Fuel> findById(Integer id);

    ArrayList<Fuel> findAll ();

    ArrayList<Fuel> findFuelByTypeIgnoreCase(String type);

}