package com.autodb_api.services;


import com.autodb_api.models.Body;
import com.autodb_api.models.Make;
import com.autodb_api.repositories.BodyRepository;
import com.autodb_api.repositories.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MakeService {

    @Autowired
    private MakeRepository repository;


    public Optional<Make> findById(Integer id) {
        return repository.findById(id);
    }


    public ArrayList<Make> findByName(String name) {
        return repository.findMakeByName(name);
    }



    public ArrayList<Make> findAll() {
        return repository.findAll();
    }
}
