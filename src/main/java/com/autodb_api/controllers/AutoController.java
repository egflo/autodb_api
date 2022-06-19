package com.autodb_api.controllers;

import com.autodb_api.dto.AutoDTO;
import com.autodb_api.services.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.Optional;

@RestController
@RequestMapping("/auto")
public class AutoController {
    @Autowired
    private AutoService autoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuto(@RequestHeader(required = false) HttpHeaders headers, @PathVariable("id") Integer id) {

        return new ResponseEntity<>(autoService.getAuto(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        // This returns a JSON or XML with the movies

        return new ResponseEntity<>(autoService.findAll(
                PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);

    }

    @GetMapping("/year/{year}")
    public ResponseEntity<?> getByYear(
            @PathVariable(value = "year") Integer year,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        // This returns a JSON or XML with the movies

        return new ResponseEntity<>(autoService.getAutoByYear(
                year, PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);

    }

    @GetMapping("/model/{model}")
    public ResponseEntity<?> getByModel(
            @PathVariable(value = "model") String model,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return new ResponseEntity<>(autoService.getAutoByModel(
                model, PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);
    }

    @GetMapping("/make/{makeName}")
    public ResponseEntity<?> getByMake(
            @PathVariable(value = "makeName") String name,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        // This returns a JSON or XML with the movies

        return new ResponseEntity<>(autoService.getAutoByMakeName(
                name, PageRequest.of(
                        page.orElse(0),
                        limit.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))),
                HttpStatus.OK);

    }

}
