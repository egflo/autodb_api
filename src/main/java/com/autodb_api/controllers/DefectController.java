package com.autodb_api.controllers;

import com.autodb_api.services.ComplaintService;
import com.autodb_api.services.DefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.Optional;

@RestController
@RequestMapping("/defect")
public class DefectController {
    @Autowired
    private DefectService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @RequestHeader(required = false) HttpHeaders headers,
            @PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/make/{make}")
    public ResponseEntity<?> getByMake(
            @RequestHeader(required = false) HttpHeaders headers,
            @PathVariable("make") String make,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        // This returns a JSON or XML with the movies
        return new ResponseEntity<>(service.findByMake(
                make,
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);
    }

    @GetMapping("/make/{make}/model/{model}")
    public ResponseEntity<?> getByMakeAndModel(
            @RequestHeader(required = false) HttpHeaders headers,
            @PathVariable("make") String make,
            @PathVariable("model") String model,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return new ResponseEntity<>(service.findByMakeAndModel(
                make,
                model,
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);

    }

    @GetMapping("/make/{make}/model{model}/year/{year}")
    public ResponseEntity<?> getByMakeAndModelAndYear(
            @RequestHeader(required = false) HttpHeaders headers,
            @PathVariable("make") String make,
            @PathVariable("model") String model,
            @PathVariable("year") Integer year,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {

        return new ResponseEntity<>(service.findByMakeAndModelAndYear(
                make,
                model,
                year,
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);
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
