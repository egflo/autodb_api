package com.autodb_api.controllers;

import com.autodb_api.services.BodyService;
import com.autodb_api.services.MakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.Optional;

@RestController
@RequestMapping("/make")
public class MakeController {
    @Autowired
    private MakeService service;

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

}
