package com.autodb_api.interfaces;

import com.autodb_api.models.Auto;
import com.autodb_api.models.Bookmark;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "BookmarkWithAuto", types = { Bookmark.class })
public interface BookmarkWithAuto {

    Bookmark getBookmark();

    Auto getAuto();
}
