package com.autodb_api.controllers;

import com.autodb_api.services.AutoService;
import com.autodb_api.services.BodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.Optional;

@RestController
@RequestMapping("/body")
public class BodyController {
    @Autowired
    private BodyService bodyService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @RequestHeader(required = false) HttpHeaders headers, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(bodyService.findById(id), HttpStatus.OK);
    }



    @GetMapping("/type/{type}")
    public ResponseEntity<?> getByType(
            @RequestHeader(required = false) HttpHeaders headers,
            @PathVariable("type") Integer type,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        // This returns a JSON or XML with the movies

        return new ResponseEntity<>(bodyService.findByBodyType(
                type,
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

        return new ResponseEntity<>(bodyService.findAll(
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);

    }


}
