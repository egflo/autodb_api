package com.autodb_api.services;


import com.autodb_api.models.Complaint;
import com.autodb_api.models.Defect;
import com.autodb_api.repositories.ComplaintRepository;
import com.autodb_api.repositories.DefectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefectService {

    @Autowired
    private DefectsRepository repository;


    public Optional<Defect> findById(Integer id) {
        return repository.findById(id);
    }

    public Page<Defect> findByMakeAndModel(String make, String model, PageRequest pageRequest) {
        return repository.findByMakeIgnoreCaseAndModelIgnoreCase(make, model, pageRequest);
    }

    public Page<Defect> findByMakeAndModelAndYear(String make, String model, Integer year, PageRequest pageRequest) {
        return repository.findByMakeIgnoreCaseAndModelIgnoreCaseAndYear(make, model, year, pageRequest);
    }

    public Page<Defect> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Page<Defect>  findByMake(String make, PageRequest pageRequest) {
        return repository.findByMakeIgnoreCase(make, pageRequest);
    }
}
