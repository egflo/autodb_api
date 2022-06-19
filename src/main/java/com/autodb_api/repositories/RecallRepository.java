package com.autodb_api.repositories;

import com.autodb_api.models.Defect;
import com.autodb_api.models.Recall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("RecallRepository")
public interface RecallRepository extends JpaRepository<Recall, Integer> {

    Optional<Recall> findById(Integer id);

    Page<Recall> findAll (Pageable page);

    Page<Recall> findByMakeIgnoreCaseAndModelIgnoreCase(String make, String model, Pageable page);

    Page<Recall> findByMakeIgnoreCaseAndModelIgnoreCaseAndYear(String make, String model, Integer year, Pageable page);

    Page<Recall>  findByMakeIgnoreCase(String make, PageRequest pageRequest);
}