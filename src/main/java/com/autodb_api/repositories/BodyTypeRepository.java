package com.autodb_api.repositories;

import com.autodb_api.models.Body;
import com.autodb_api.models.BodyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository("BodyTypeRepository")
public interface BodyTypeRepository extends JpaRepository<BodyType, Integer> {

    Optional<BodyType> findById(Integer id);

    ArrayList<BodyType> findAll ();

    ArrayList<BodyType> findBodyTypeByTypeIgnoreCase(String type);

}