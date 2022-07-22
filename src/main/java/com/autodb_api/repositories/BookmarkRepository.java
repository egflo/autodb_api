package com.autodb_api.repositories;

import com.autodb_api.interfaces.BookmarkWithAuto;
import com.autodb_api.models.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository("BookmarkRepository")
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {


    Optional<Bookmark> findById(Integer id);

    Optional<Bookmark> findByUserIdAndAutoId(String userId, Integer autoId);


    Page<Bookmark> findAll(Pageable pageable);


    @Query("SELECT a as auto, b as bookmark FROM Bookmark b LEFT JOIN Auto a ON a.id = b.autoId WHERE b.userId = ?1")
    Page<BookmarkWithAuto> findByUserId(String userId, Pageable pageable);

    Page<Bookmark> findByAutoId(Integer autoId, Pageable pageable);


    Page<Bookmark> findByUserIdAndAutoIdAndCreated(String userId, Integer autoId, LocalDate created, Pageable pageable);

    Boolean existsByUserIdAndAutoId(String userId, Integer autoId);

    List<Bookmark> findAllByUserId(String userId);
}