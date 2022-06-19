package com.autodb_api.repositories;

import com.autodb_api.models.Body;
import com.autodb_api.models.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("ComplaintRepository")
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

    Optional<Complaint> findById(Integer id);

    Page<Complaint> findAll (Pageable page);

    Page<Complaint> findByMakeIgnoreCaseAndModelIgnoreCase(String make, String model, Pageable page);

    Page<Complaint> findByMakeIgnoreCaseAndModelIgnoreCaseAndYear(String make, String model, Integer year, Pageable page);

    Page<Complaint>  findByMakeIgnoreCase(String make, PageRequest pageRequest);
}