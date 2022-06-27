package com.autodb_api.repositories;

import com.autodb_api.models.BodyType;
import com.autodb_api.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("ColorRepository")
public interface ColorRepository extends JpaRepository<Color, Integer> {

    Optional<Color> findById(Integer id);

    ArrayList<Color> findAll ();

    ArrayList<Color> findColorByNameContainingIgnoreCase(String type);

}