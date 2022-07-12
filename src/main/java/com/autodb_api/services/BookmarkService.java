package com.autodb_api.services;


import com.autodb_api.dto.WatchlistDTO;
import com.autodb_api.models.Bookmark;
import com.autodb_api.repositories.BookmarkRepository;
import com.autodb_api.response.ResponseHTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return repository.findByUserId(id);
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

    public ResponseHTTP findByUserIdAndAutoId(String userId, Integer autoId) {
        Optional<Bookmark> bookmark = repository.findByUserIdAndAutoId(userId, autoId);
        if (bookmark.isPresent()) {
            return new ResponseHTTP(200, "Bookmark found", bookmark.get(), true);
        } else {
            return new ResponseHTTP(404, "Bookmark not found", null, false);
        }
    }

    public ResponseHTTP add(WatchlistDTO request) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(request.getUserId());
        bookmark.setAutoId(request.getAutoId());
        bookmark.setCreated(new Date());

        ResponseHTTP response = new ResponseHTTP(200, "Bookmark added successfully", repository.save(bookmark), true);
        return response;
    }

    public ResponseHTTP update(WatchlistDTO request) {
        Bookmark bookmark = new Bookmark();
        bookmark.setId(request.getId());
        bookmark.setUserId(request.getUserId());
        bookmark.setAutoId(request.getAutoId());
        bookmark.setCreated(new Date());

        ResponseHTTP response = new ResponseHTTP(200, "Bookmark updated successfully", bookmark, true);
        return response;
    }

    public ResponseHTTP delete(Integer id) {

        ResponseHTTP response = new ResponseHTTP(200, "Bookmark deleted successfully", null, true);
        return response;
    }
}
