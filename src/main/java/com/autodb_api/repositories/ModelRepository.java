package com.autodb_api.repositories;

import com.autodb_api.models.Make;
import com.autodb_api.models.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("ModelRepository")
public interface ModelRepository extends JpaRepository<Model, Integer> {

    Optional<Model> findById(Integer id);

    Page<Model> findAll (Pageable pageable);

    ArrayList<Model> findModelByName(String name);

    ArrayList<Model> findAll();


    ArrayList<Model> getModelsByMakeId(Integer makeId);
}