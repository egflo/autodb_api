package com.autodb_api.repositories;

import com.autodb_api.models.Body;
import com.autodb_api.models.Make;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("MakeRepository")
public interface MakeRepository extends JpaRepository<Make, Integer> {

    Optional<Make> findById(Integer id);

    Page<Make> findAll (Pageable pageable);

    ArrayList<Make> findMakeByName(String name);

    ArrayList<Make> findAll();


}