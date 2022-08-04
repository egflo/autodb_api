package com.autodb_api.services;


import com.autodb_api.models.Make;
import com.autodb_api.models.Model;
import com.autodb_api.repositories.MakeRepository;
import com.autodb_api.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ModelService {

    @Autowired
    private ModelRepository repository;


    public Optional<Model> findById(Integer id) {
        return repository.findById(id);
    }


    public ArrayList<Model> findByName(String name) {
        return repository.findModelByName(name);
    }



    public ArrayList<Model> findAll() {
        return repository.findAll();
    }

    public ArrayList<Model> getModelsByMake(Integer makeId) {
        return repository.getModelsByMakeId(makeId);
    }
}
