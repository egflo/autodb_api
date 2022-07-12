package com.autodb_api.graphql;

import com.autodb_api.models.Auto;
import com.autodb_api.repositories.AutoRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private AutoRepository repository;

    public Auto autoById(Integer id) {
        // Not returning a fixed instance anymore
        return repository.findById(id).orElse(null);
    }
}
