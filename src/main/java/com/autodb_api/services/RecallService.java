package com.autodb_api.services;


import com.autodb_api.models.Defect;
import com.autodb_api.models.Recall;
import com.autodb_api.repositories.DefectsRepository;
import com.autodb_api.repositories.RecallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecallService {

    @Autowired
    private RecallRepository repository;


    public Optional<Recall> findById(Integer id) {
        return repository.findById(id);
    }

    public Page<Recall> findByMakeAndModel(String make, String model, PageRequest pageRequest) {
        return repository.findByMakeIgnoreCaseAndModelIgnoreCase(make, model, pageRequest);
    }

    public Page<Recall> findByMakeAndModelAndYear(String make, String model, Integer year, PageRequest pageRequest) {
        return repository.findByMakeIgnoreCaseAndModelIgnoreCaseAndYear(make, model, year, pageRequest);
    }

    public Page<Recall> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Page<Recall>  findByMake(String make, PageRequest pageRequest) {
        return repository.findByMakeIgnoreCase(make, pageRequest);
    }
}
