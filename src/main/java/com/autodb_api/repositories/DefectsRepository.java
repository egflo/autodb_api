package com.autodb_api.repositories;

import com.autodb_api.models.Complaint;
import com.autodb_api.models.Defect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("DefectsRepository")
public interface DefectsRepository extends JpaRepository<Defect, Integer> {

    Optional<Defect> findById(Integer id);

    Page<Defect> findAll (Pageable page);

    Page<Defect> findByMakeIgnoreCaseAndModelIgnoreCase(String make, String model, Pageable page);

    Page<Defect> findByMakeIgnoreCaseAndModelIgnoreCaseAndYear(String make, String model, Integer year, Pageable page);

    Page<Defect>  findByMakeIgnoreCase(String make, PageRequest pageRequest);
}