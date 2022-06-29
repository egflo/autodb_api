package com.autodb_api.models;

import javax.persistence.*;

@Entity
@Table(name = "stock_images")
public class StockImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private String year;

    @Column(name = "image_angular_front")
    private String imageAngularFront;

    @Column(name = "image_front")
    private String imageFront;

    @Column(name = "image_rear")
    private String imageRear;

    @Column(name = "image_side")
    private String imageSide;

    @Column(name = "cached")
    private Integer cached;

    @Column(name = "image_angular_rear")
    private String imageAngularRear;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageAngularFront() {
        return imageAngularFront;
    }

    public void setImageAngularFront(String imageAngularFront) {
        this.imageAngularFront = imageAngularFront;
    }

    public String getImageFront() {
        return imageFront;
    }

    public void setImageFront(String imageFront) {
        this.imageFront = imageFront;
    }

    public String getImageRear() {
        return imageRear;
    }

    public void setImageRear(String imageRear) {
        this.imageRear = imageRear;
    }

    public String getImageSide() {
        return imageSide;
    }

    public void setImageSide(String imageSide) {
        this.imageSide = imageSide;
    }

    public Integer getCached() {
        return cached;
    }

    public void setCached(Integer cached) {
        this.cached = cached;
    }

    public String getImageAngularRear() {
        return imageAngularRear;
    }

    public void setImageAngularRear(String imageAngularRear) {
        this.imageAngularRear = imageAngularRear;
    }

}