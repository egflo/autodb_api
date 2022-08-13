package com.autodb_api.repositories;

import com.autodb_api.models.BodyType;
import com.autodb_api.models.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("SearchRepository")
public interface SearchRepository extends JpaRepository<Search, Integer> {

    Optional<Search> findById(Integer id);

    ArrayList<Search> findAll ();

    ArrayList<Search> findByUserId(String userId);

    ArrayList<Search> findByDescriptionContaining(String description);

}