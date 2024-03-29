package com.autodb_api.services;


import com.autodb_api.dto.WatchlistDTO;
import com.autodb_api.interfaces.BookmarkWithAuto;
import com.autodb_api.models.Bookmark;
import com.autodb_api.repositories.BookmarkRepository;
import com.autodb_api.response.ResponseHTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository repository;


    public Page<Bookmark> findAll(PageRequest pageable) {
        Page<Bookmark> watchlists = repository.findAll(pageable);
        return watchlists;
    }

    public Optional<Bookmark> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Bookmark> findByUserId(String id) {
        return repository.findAllByUserId(id);
    }

    public Page<BookmarkWithAuto> findByUserId(String id, PageRequest pageable) {
        return repository.findByUserId(id, pageable);
    }

    public Page<Bookmark> findByAutoId(Integer id, PageRequest pageRequest) {
        return repository.findByAutoId(id, pageRequest);
    }


    public Page<Bookmark> findByUserIdAndAutoIdAndCreated(String userId, Integer autoId, LocalDate created, PageRequest pageRequest) {
        return repository.findByUserIdAndAutoIdAndCreated(userId, autoId, created, pageRequest);
    }

    public Boolean existsByUserIdAndAutoId(String userId, Integer autoId) {
        return repository.existsByUserIdAndAutoId(userId, autoId);
    }

    public ResponseEntity<?> findByUserIdAndAutoId(String userId, Integer autoId) {
        Optional<Bookmark> bookmark = repository.findByUserIdAndAutoId(userId, autoId);
        if (bookmark.isPresent()) {
            return ResponseEntity.ok(bookmark.get());
        } else {
            return new ResponseEntity<>("Bookmark not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> add(WatchlistDTO request) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(request.getUserId());
        bookmark.setAutoId(request.getAutoId());
        bookmark.setCreated(new Date());

        return new ResponseEntity<>(repository.save(bookmark), HttpStatus.CREATED);
    }

    public ResponseEntity<?>  update(WatchlistDTO request) {
        Optional<Bookmark> update = repository.findByUserIdAndAutoId(request.getUserId(), request.getAutoId());
        if (update.isPresent()) {
            delete(update.get().getId());
            return ResponseEntity.ok(update.get());

        } else {
            return add(request);
        }
    }

    public ResponseEntity<?> delete(Integer id) {

        Optional<Bookmark> delete = repository.findById(id);
        if (delete.isPresent()) {
            repository.delete(delete.get());
            return ResponseEntity.ok("Bookmark deleted");
        } else {
            return ResponseEntity.badRequest().body("Bookmark not found");
        }
    }
}
