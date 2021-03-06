package com.autodb_api.models;

import javax.persistence.*;

@Entity
@Table(name = "\"trim\"")
public class Trim {
    @Id
    @Column(name = "id", nullable = true)
    private String id;

    @Column(name = "description", nullable = true)
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}