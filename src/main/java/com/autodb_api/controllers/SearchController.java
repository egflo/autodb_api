package com.autodb_api.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.autodb_api.dto.SearchDTO;
import com.autodb_api.services.MakeService;
import com.autodb_api.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.Optional;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private SearchService service;

    @PostMapping("/")
    public ResponseEntity<?> addSearch(
            @RequestHeader(required = true) org.springframework.http.HttpHeaders headers,
            @RequestBody SearchDTO searchDTO) {
        String token = headers.get("authorization").get(0).split(" ")[1].trim();
        DecodedJWT jwt = JWT.decode(token);
        String subject = jwt.getSubject();

        searchDTO.setUserId(subject);
        return new ResponseEntity<>(service.addSearch(searchDTO), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAll(
            @RequestHeader(required = true) org.springframework.http.HttpHeaders headers,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        // This returns a JSON or XML with the movies
        String token = headers.get("authorization").get(0).split(" ")[1].trim();
        DecodedJWT jwt = JWT.decode(token);
        String subject = jwt.getSubject();

        return new ResponseEntity<>(service.findByUserId(subject), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @RequestHeader(required = false) HttpHeaders headers, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getByName(
            @RequestHeader(required = false) HttpHeaders headers, @PathVariable("name") String name) {
        return new ResponseEntity<>(service.findByDescription(name), HttpStatus.OK);
    }

}
