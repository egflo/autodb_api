package com.autodb_api.services;


import com.autodb_api.models.Body;
import com.autodb_api.models.Complaint;
import com.autodb_api.repositories.BodyRepository;
import com.autodb_api.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository repository;


    public Optional<Complaint> findById(Integer id) {
        return repository.findById(id);
    }

    public Page<Complaint> findByMakeAndModel(String make, String model, PageRequest pageRequest) {
        return repository.findByMakeIgnoreCaseAndModelIgnoreCase(make, model, pageRequest);
    }

    public Page<Complaint> findByMakeAndModelAndYear(String make, String model, Integer year, PageRequest pageRequest) {
        return repository.findByMakeIgnoreCaseAndModelIgnoreCaseAndYear(make, model, year, pageRequest);
    }

    public Page<Complaint> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Object findByMake(String make, PageRequest pageRequest) {
        return repository.findByMakeIgnoreCase(make, pageRequest);
    }
}
