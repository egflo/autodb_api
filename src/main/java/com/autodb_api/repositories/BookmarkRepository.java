package com.autodb_api.repositories;

import com.autodb_api.models.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("BookmarkRepository")
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    Optional<Bookmark> findById(Integer id);

    Optional<Bookmark> findByUserIdAndAutoId(String userId, Integer autoId);

    Page<Bookmark> findAll(Pageable pageable);

    List<Bookmark> findByUserId(String userId);

    Page<Bookmark> findByAutoId(Integer autoId, Pageable pageable);


    Page<Bookmark> findByUserIdAndAutoIdAndCreated(String userId, Integer autoId, LocalDate created, Pageable pageable);

    Boolean existsByUserIdAndAutoId(String userId, Integer autoId);
}