package com.autodb_api.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
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



    @RequestMapping(value = "/postcode/{postcode}/{miles}", method = RequestMethod.GET)
    public ResponseEntity<?> getAutoByPostcode(
            @PathVariable String postcode,
            @PathVariable Integer miles)
    {
        return new ResponseEntity<>(autoService.getAutoByPostcode(postcode, miles), HttpStatus.OK);
    }

    @RequestMapping(value = PATH_VARIABLE_PATTERN_SEARCH, method = RequestMethod.GET)
    public ResponseEntity<Page<Auto>> search(
            HttpServletRequest request,
            @RequestParam Optional<String> colorCode,
            @RequestParam Optional<String> bodyCode,
            @RequestParam Optional<String> drivetrainCode,
            @RequestParam Optional<String> fuelCode,
            @RequestParam Optional<String> transmissionCode,
            @RequestParam Optional<Integer> startYear,
            @RequestParam Optional<Integer> endYear,
            @RequestParam Optional<Double> mileage,
            @RequestParam Optional<Integer> postcode,
            @RequestParam Optional<Integer> radius,
            @RequestParam Optional<Double> priceMin,
            @RequestParam Optional<Double> priceMax,
            @RequestParam Optional<String> conditionCode,
            @RequestParam Optional<String> modelCode,
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> sortDirection,
            @RequestParam Optional<String> sortBy) throws URISyntaxException {


        Sort.Direction direction = Sort.Direction.ASC;
        if (sortDirection.isPresent() && sortDirection.get() == 1) {
            direction = Sort.Direction.DESC;
        }

        Pageable pageable =
                PageRequest.of(page.orElse(0), limit.orElse(10), direction, sortBy.orElse("id"));

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

        return new ResponseEntity<>(autoService.search(params, colorCode, bodyCode,
                drivetrainCode, fuelCode, transmissionCode, startYear, endYear, mileage,
                postcode, radius, priceMin, priceMax, conditionCode, modelCode,
                pageable), HttpStatus.OK);
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
    public ResponseEntity<?> getAuto(@RequestHeader(required = false) org.springframework.http.HttpHeaders headers, @PathVariable("id") Integer id) {


        if(headers.get("authorization") == null) {
            return new ResponseEntity<>(autoService.getAuto(id), HttpStatus.OK);
        }

        String token = headers.get("authorization").get(0).split(" ")[1].trim();
        DecodedJWT jwt = JWT.decode(token);
        return new ResponseEntity<>(autoService.getAuto(id, jwt.getSubject()), HttpStatus.OK);

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
        return new ResponseEntity<>(autoService.getAutoByMakeName(
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
