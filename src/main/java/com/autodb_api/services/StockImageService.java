package com.autodb_api.services;


import com.autodb_api.models.Body;
import com.autodb_api.models.BodyType;
import com.autodb_api.models.StockImage;
import com.autodb_api.repositories.BodyRepository;
import com.autodb_api.repositories.BodyTypeRepository;
import com.autodb_api.repositories.StockImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StockImageService {

    @Autowired
    private StockImageRepository repository;

    public Optional<StockImage>  findByMakeModelYear(String make, String model, Integer year) {
        return repository.findAllByMakeIgnoreCaseAndModelIgnoreCaseAndYear(make, model, year.toString());
    }

    public Optional<StockImage> findById(Integer id) {
        return repository.findById(id);
    }


    public Page<StockImage> findByMake(String make, PageRequest pageRequest) {
        return repository.findAllByMakeIgnoreCase(make, pageRequest);
    }


    public Page<StockImage> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }
}
