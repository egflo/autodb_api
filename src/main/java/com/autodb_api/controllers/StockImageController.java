package com.autodb_api.controllers;

import com.autodb_api.services.BodyService;
import com.autodb_api.services.StockImageService;
import com.azure.core.annotation.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.Optional;

@RestController
@RequestMapping("/stock-image")
public class StockImageController {
    @Autowired
    private StockImageService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @RequestHeader(required = false) HttpHeaders headers, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{make}/{model}/{year}")
    public ResponseEntity<?> getByMakeModelYear(
            @RequestHeader(required = false) HttpHeaders headers, @PathVariable("make") String make, @PathVariable("model") String model, @PathVariable("year") Integer year) {
        return new ResponseEntity<>(service.findByMakeModelYear(make, model, year), HttpStatus.OK);
    }

    @GetMapping("/make/{make}")
    public ResponseEntity<?> getByMake(
            @RequestHeader(required = false) HttpHeaders headers,
            @PathVariable("make") String make,
            @RequestParam Optional<Integer> limit, @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) {
        return  new ResponseEntity<>(service.findByMake(make,
                PageRequest.of(page.orElse(0),
                        limit.orElse(10),
                        Sort.by(sortBy.orElse("id")))), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAll(
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        // This returns a JSON or XML with the movies

        return new ResponseEntity<>(service.findAll(
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);

    }


}
