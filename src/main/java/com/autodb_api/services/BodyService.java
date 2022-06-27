package com.autodb_api.services;


import com.autodb_api.models.Auto;
import com.autodb_api.models.Body;
import com.autodb_api.models.BodyType;
import com.autodb_api.repositories.AutoRepository;
import com.autodb_api.repositories.BodyRepository;
import com.autodb_api.repositories.BodyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BodyService {

    @Autowired
    private BodyRepository repository;

    @Autowired
    BodyTypeRepository bodyTypeRepository;

    public ArrayList<BodyType> getAllBodyTypes() {
        return bodyTypeRepository.findAll();
    }

    public Optional<Body> findById(Integer id) {
        return repository.findById(id);
    }


    public Page<Body> findByBodyType(Integer id, PageRequest pageRequest) {
        return repository.findByBodyType(id, pageRequest);
    }


    public Page<Body> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }
}
