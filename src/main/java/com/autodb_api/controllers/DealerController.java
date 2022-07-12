package com.autodb_api.controllers;

import com.autodb_api.services.BodyService;
import com.autodb_api.services.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.Optional;

@RestController
@RequestMapping("/dealer")
public class DealerController {
    @Autowired
    private DealerService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @RequestHeader(required = false) HttpHeaders headers, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/postcode/{postcode}/{distance}")
    public ResponseEntity<?> getByPostcode(
            @RequestHeader(required = false) HttpHeaders headers,
            @PathVariable("postcode") Integer postcode,
            @PathVariable("distance") Integer distance) {

        return new ResponseEntity<>(service.findAllByPostCode(postcode, distance), HttpStatus.OK);
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
