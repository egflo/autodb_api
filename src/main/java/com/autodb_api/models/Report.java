package com.autodb_api.models;


import javax.persistence.*;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "vin")
    private String vin;

    @Column(name = "frame_damage")
    private Boolean frameDamage;

    @Column(name = "has_accidents")
    private Boolean hasAccidents;

    @Column(name = "theft_title")
    private Boolean theftTitle;

    @Column(name = "owner_count")
    private Integer ownerCount;

    @Column(name = "salvage")
    private Boolean salvage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Boolean getFrameDamage() {
        return frameDamage;
    }

    public void setFrameDamage(Boolean frameDamage) {
        this.frameDamage = frameDamage;
    }

    public Boolean getHasAccidents() {
        return hasAccidents;
    }

    public void setHasAccidents(Boolean hasAccidents) {
        this.hasAccidents = hasAccidents;
    }

    public Boolean getTheftTitle() {
        return theftTitle;
    }

    public void setTheftTitle(Boolean theftTitle) {
        this.theftTitle = theftTitle;
    }

    public Integer getOwnerCount() {
        return ownerCount;
    }

    public void setOwnerCount(Integer ownerCount) {
        this.ownerCount = ownerCount;
    }

    public Boolean getSalvage() {
        return salvage;
    }

    public void setSalvage(Boolean salvage) {
        this.salvage = salvage;
    }

}