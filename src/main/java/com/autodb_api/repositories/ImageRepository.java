package com.autodb_api.repositories;

import com.autodb_api.models.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("ImageRepository")
public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findById(Integer id);

    Optional<Image> findByUrl(String url);

    Page<Image> findAll (Pageable pageable);

    Image save(Image image);

}