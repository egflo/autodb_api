package com.autodb_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import javax.persistence.*;

@Entity
@Table(name = "dealer")
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "name")
    private String name;

    @Column(name = "sp_id")
    private Double spId;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "city")
    private String city;

    @Column(name = "franchise_dealer")
    private Boolean franchiseDealer;

    @Column(name = "franchise_make")
    private String franchiseMake;


    @JsonIgnore
    @Column(name = "location", columnDefinition = "geography(Point, 4326)")
    private Point location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSpId() {
        return spId;
    }

    public void setSpId(Double spId) {
        this.spId = spId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getFranchiseDealer() {
        return franchiseDealer;
    }

    public void setFranchiseDealer(Boolean franchiseDealer) {
        this.franchiseDealer = franchiseDealer;
    }

    public String getFranchiseMake() {
        return franchiseMake;
    }

    public void setFranchiseMake(String franchiseMake) {
        this.franchiseMake = franchiseMake;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }


}