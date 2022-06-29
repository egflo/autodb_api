package com.autodb_api.repositories;

import com.autodb_api.models.Image;
import com.autodb_api.models.StockImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("StockImageRepository")
public interface StockImageRepository extends JpaRepository<StockImage, Integer> {

    Optional<StockImage> findById(Integer id);

    Optional<StockImage> findAllByMakeIgnoreCaseAndModelIgnoreCaseAndYear(String make, String model, String year);

    Page<StockImage> findAll (Pageable pageable);


    Page<StockImage> findAllByMakeIgnoreCase(String make, PageRequest pageRequest);
}