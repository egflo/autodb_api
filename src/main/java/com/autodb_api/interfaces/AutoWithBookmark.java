package com.autodb_api.interfaces;

import com.autodb_api.models.Auto;
import com.autodb_api.models.Bookmark;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "AutoWithBookmark", types = { Auto.class })
public interface AutoWithBookmark {

        Auto getAuto();

        Bookmark getBookmark();
}
