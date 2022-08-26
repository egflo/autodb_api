package com.autodb_api.graphql;

import com.autodb_api.dao.AutoDao;
import com.autodb_api.dto.AutoDTO;
import com.autodb_api.models.Auto;
import com.autodb_api.models.PageInfo;
import com.autodb_api.repositories.AutoRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;





@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private AutoRepository repository;

    @Autowired
    private AutoDao autoDao;


    public Auto autoById(Integer id) {
        // Not returning a fixed instance anymore
        return repository.findById(id).orElse(null);
    }

    public PageInfo searchAuto(
            String query,
            Optional<String> color_code,
            Optional<String> body_code,
            Optional<String> drivetrain_code,
            Optional<String> fuel_code,
            Optional<String> transmission_code,
            Optional<Integer> start_year,
            Optional<Integer> end_year,
            Optional<Double> mileage,
            Optional<Integer> postcode,
            Optional<Integer> radius,
            Optional<Double> price_min,
            Optional<Double> price_max,
            Optional<String> condition_code,
            Optional<String> model_code,
            Optional<Integer> limit,
            Optional<Integer> page,
            Optional<Integer> sortDirection,
            Optional<String> sortBy) {


        Sort.Direction direction = Sort.Direction.ASC;
        if (sortDirection.isPresent() && sortDirection.get() == 1) {
            direction = Sort.Direction.DESC;
        }

        Pageable pageable =
                PageRequest.of(page.orElse(0), limit.orElse(10), direction, sortBy.orElse("id"));


        String[] queries = query.split("/");

        //Slice the array to bypass search and get the actual query
        ArrayList<String> params = new ArrayList<>();
        for(String term : queries) {
            if(term.contains("search") || term.contains("auto") || term.isBlank()) {
                continue;
            } else {
                params.add(query);
            }
        }

        Page<Auto> data = autoDao.findAutoByParams(params, color_code, body_code,
                drivetrain_code, fuel_code, transmission_code, start_year, end_year, mileage,
                postcode, radius, price_min, price_max, condition_code, model_code,
                pageable);

        PageInfo pageInfo = new PageInfo(data.getPageable(), data.isLast(),
                data.getTotalPages(), data.getTotalElements(), data.isFirst(), data.getSize(), data.getNumber(), data.getSort(), data.getNumberOfElements(), data.isEmpty(), data.getContent());

        return pageInfo;
    }
}
