package com.autodb_api.services;


import com.autodb_api.dto.SearchDTO;
import com.autodb_api.dto.WatchlistDTO;
import com.autodb_api.interfaces.BookmarkWithAuto;
import com.autodb_api.models.Bookmark;
import com.autodb_api.models.Search;
import com.autodb_api.repositories.BookmarkRepository;
import com.autodb_api.repositories.SearchRepository;
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
public class SearchService {

    @Autowired
    private SearchRepository repository;


    public Page<Search> findAll(PageRequest pageable) {
        Page<Search> watchlists = repository.findAll(pageable);
        return watchlists;
    }

    public Optional<Search> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Search> findByUserId(String id) {
        return repository.findByUserId(id);
    }

    public List<Search> findByDescription(String description) {
        return repository.findByDescriptionContaining(description);
    }

    public Page<Search> findall(String description, PageRequest pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Search> addSearch(SearchDTO searchDTO) {
        Search search = new Search();
        search.setUserId(searchDTO.getUserId());
        search.setDescription(searchDTO.getDescription());
        search.setQuery(searchDTO.getQuery());
        search.setCreated(new Date());


        return Optional.of(repository.save(search));
    }

    public void deleteById(Integer id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("No search found with id " + id);
        }
    }
}
