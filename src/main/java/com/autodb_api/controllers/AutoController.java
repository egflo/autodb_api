package com.autodb_api.controllers;

import com.autodb_api.dto.AutoDTO;
import com.autodb_api.models.Auto;
import com.autodb_api.services.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/auto")
public class AutoController {
    @Autowired
    private AutoService autoService;


    private static final String PATH_VARIABLE_PATTERN_SEARCH = "/search/**";

    @RequestMapping(value = PATH_VARIABLE_PATTERN_SEARCH, method = RequestMethod.GET)
    public ResponseEntity<Page<Auto>> search(
            HttpServletRequest request,
            @RequestParam Optional<String> color_code,
            @RequestParam Optional<String> body_code,
            @RequestParam Optional<String> drivetrain_code,
            @RequestParam Optional<String> fuel_code,
            @RequestParam Optional<String> transmission_code,
            @RequestParam Optional<Integer> start_year,
            @RequestParam Optional<Integer> end_year,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy) throws URISyntaxException {

        Pageable pageable = PageRequest.of(page.orElse(0), limit.orElse(10), Sort.by(sortBy.orElse("id")));

        //Get URL from request
        String url = request.getRequestURL().toString();
        URI uri = new URI(url);
        String path = uri.getPath(); // split whatever you need
        String[] queries = path.split("/");

        //Slice the array to bypass search and get the actual query
        ArrayList<String> params = new ArrayList<>();
        for(String query : queries) {
            if(query.contains("search") || query.contains("auto") || query.isBlank()) {
                continue;
            } else {
                params.add(query);
            }
        }

        return new ResponseEntity<>(autoService.search(params, color_code, body_code,
                drivetrain_code, fuel_code, transmission_code, start_year, end_year, pageable), HttpStatus.OK);

    }
    @GetMapping("/transmission/all")
    public ResponseEntity<?> getAllTransmissions() {
        return new ResponseEntity<>(autoService.getAllTransmissions(), HttpStatus.OK);
    }

    @GetMapping("/drivetrain/all")
    public ResponseEntity<?> getAllDrivetrains() {
        return new ResponseEntity<>(autoService.getAllDrivetrains(), HttpStatus.OK);
    }

    @GetMapping("/color/all")
    public ResponseEntity<?> getAllColors() {
        return new ResponseEntity<>(autoService.getAllColors(), HttpStatus.OK);
    }

    @GetMapping("/fuel/all")
    public ResponseEntity<?> getAllFuels() {
        return new ResponseEntity<>(autoService.getAllFuels(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuto(@RequestHeader(required = false) HttpHeaders headers, @PathVariable("id") Integer id) {

        return new ResponseEntity<>(autoService.getAuto(id), HttpStatus.OK);
    }

    @GetMapping("/{make}/model/all")
    public ResponseEntity<?> getAllAuto(@RequestHeader(required = false) HttpHeaders headers, @PathVariable("make") String make) {
        return new ResponseEntity<>(autoService.getAllModelsByMake(make), HttpStatus.OK);
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
