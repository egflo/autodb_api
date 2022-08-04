package com.autodb_api.controllers;

import com.autodb_api.services.MakeService;
import com.autodb_api.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    private ModelService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @RequestHeader(required = false) HttpHeaders headers, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(
            @RequestHeader(required = false) HttpHeaders headers, @PathVariable("name") String name) {
        return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/make/{makeId}")
    public ResponseEntity<?> create(
            @RequestHeader(required = false) HttpHeaders headers, @PathVariable("makeId") Integer makeId) {
        return new ResponseEntity<>(service.getModelsByMake(makeId), HttpStatus.OK);
    }

}
