package com.autodb_api.dto;

import com.autodb_api.models.Auto;
import com.autodb_api.models.Bookmark;

import java.util.Optional;

public class AutoWithBookmarkDTO {

    private Auto auto;
    private Optional<Bookmark> bookmark;

    public AutoWithBookmarkDTO() {
    }

    public AutoWithBookmarkDTO(Auto auto, Optional<Bookmark> bookmark) {
        this.auto = auto;
        this.bookmark = bookmark;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Optional<Bookmark> getBookmark() {
        return bookmark;
    }

    public void setBookmark(Optional<Bookmark> bookmark) {
        this.bookmark = bookmark;
    }
}
