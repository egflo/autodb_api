package com.autodb_api.models;

import javax.persistence.*;

@Entity
@Table(name = "make")
public class Make {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {

        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public void setName(String name) {
        this.name = name;
    }

}