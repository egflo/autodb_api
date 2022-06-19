package com.autodb_api.models;

import javax.persistence.*;

@Entity
@Table(name = "defects")
public class Defect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "part_name")
    private String partName;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "open_date")
    private String openDate;

    @Column(name = "close_date")
    private String closeDate;

    @Column(name = "campaign_number")
    private String campaignNumber;

    @Column(name = "subject")
    private String subject;

    @Column(name = "summary")
    private String summary;

    @Column(name = "nhtsa_action_number")
    private String nhtsaActionNumber;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getCampaignNumber() {
        return campaignNumber;
    }

    public void setCampaignNumber(String campaignNumber) {
        this.campaignNumber = campaignNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNhtsaActionNumber() {
        return nhtsaActionNumber;
    }

    public void setNhtsaActionNumber(String nhtsaActionNumber) {
        this.nhtsaActionNumber = nhtsaActionNumber;
    }

}