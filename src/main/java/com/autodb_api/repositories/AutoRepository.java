package com.autodb_api.repositories;

import com.autodb_api.models.Auto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("AutoRepository")
public interface AutoRepository extends JpaRepository<Auto, Integer> {

    Optional<Auto> findById(Integer id);

    Page<Auto> findAll (Pageable pageable);

    @Query("SELECT a FROM Auto a WHERE LOWER(a.modelName) LIKE %?1%")
    Page<Auto> findAutoByModelNameLike(String name, Pageable pageable);

    @Query("SELECT a FROM Auto a WHERE LOWER(a.make.name) LIKE %?1%")
    Page<Auto> findByMakeName(String name, Pageable pageable);

    Page<Auto> findByYear(Integer year, Pageable pageable);

    @Query("SELECT a FROM Auto a WHERE LOWER(a.make.name) LIKE %?1% AND a.year = ?2")
    Page<Auto> findByMakeNameAndYear(String name, Integer year, Pageable pageable);

    @Query("SELECT a FROM Auto a WHERE LOWER(a.make.name) LIKE %?1% AND a.year = ?2 AND a.modelName LIKE %?3%")
    Page<Auto> findByMakeNameAndYearAndModelName(String name, Integer year, String modelName, Pageable pageable);


    @Query("SELECT DISTINCT a.modelName FROM Auto a WHERE LOWER(a.make.name) = LOWER(?1)")
    ArrayList<String> findModelsByMake(String name);
}