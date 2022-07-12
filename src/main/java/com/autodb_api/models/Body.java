package com.autodb_api.models;

import javax.persistence.*;

@Entity
@Table(name = "body")
public class Body {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bodyType", referencedColumnName = "id")
    private BodyType bodyType;

    @Column(name = "length")
    private String length;

    @Column(name = "width")
    private String width;

    @Column(name = "height")
    private String height;

    @Column(name = "wheelbase")
    private String wheelbase;

    @Column(name = "front_legroom")
    private String frontLegroom;

    @Column(name = "back_legroom")
    private String backLegroom;

    @Column(name = "bed_length")
    private String bedLength;

    @Column(name = "cabin")
    private String cabin;

    @Column(name = "vin")
    private String vin;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWheelbase() {
        return wheelbase;
    }

    public void setWheelbase(String wheelbase) {
        this.wheelbase = wheelbase;
    }

    public String getFrontLegroom() {
        return frontLegroom;
    }

    public void setFrontLegroom(String frontLegroom) {
        this.frontLegroom = frontLegroom;
    }

    public String getBackLegroom() {
        return backLegroom;
    }

    public void setBackLegroom(String backLegroom) {
        this.backLegroom = backLegroom;
    }

    public String getBedLength() {
        return bedLength;
    }

    public void setBedLength(String bedLength) {
        this.bedLength = bedLength;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }


   // public String getVin() {
    //    return vin;
    //}

    //public void setVin(String vin) {
     //   this.vin = vin;
   // }

}