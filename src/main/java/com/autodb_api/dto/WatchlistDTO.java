package com.autodb_api.dto;

public class WatchlistDTO {
    int id;
    int autoId;
    String userId;

    public WatchlistDTO() {
    }

    public WatchlistDTO(int autoId, String userId, int id) {
        this.id = id;
        this.autoId = autoId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
