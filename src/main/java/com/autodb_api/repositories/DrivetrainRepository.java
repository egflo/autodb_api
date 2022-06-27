package com.autodb_api.repositories;

import com.autodb_api.models.Color;
import com.autodb_api.models.Drivetrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("DrivetrainRepository")
public interface DrivetrainRepository extends JpaRepository<Drivetrain, Integer> {

    Optional<Drivetrain> findById(Integer id);

    ArrayList<Drivetrain> findAll ();

    ArrayList<Drivetrain> findDrivetrainByDescriptionContainingIgnoreCase(String type);

}