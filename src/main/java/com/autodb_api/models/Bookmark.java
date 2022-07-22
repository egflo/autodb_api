package com.autodb_api.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "watchlist")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "auto_id", nullable = false)
    private Integer autoId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "created", nullable = false)
    private Date created;

   // @OneToOne(cascade = CascadeType.ALL)
   // @JoinColumn(name = "auto_id", referencedColumnName = "id" , nullable = false, insertable = false, updatable = false)
    //private Auto auto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

   // public Auto getAuto() {
    //    return auto;
   // }

    //public void setAuto(Auto auto) {
     //   this.auto = auto;
   // }

}