package com.autodb_api.models;

import javax.persistence.*;

@Entity
@Table(name = "recalls")
public class Recall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "component_description")
    private String componentDescription;

    @Column(name = "begin_date")
    private String beginDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "units_affected")
    private Integer unitsAffected;

    @Column(name = "notified_date")
    private String notifiedDate;

    @Column(name = "initiator")
    private String initiator;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "defect_summary")
    private String defectSummary;

    @Column(name = "fmvss")
    private String fmvss;

    @Column(name = "consequence_summary")
    private String consequenceSummary;

    @Column(name = "corrective_summary")
    private String correctiveSummary;

    @Column(name = "notes")
    private String notes;

    @Column(name = "component_id")
    private String componentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getUnitsAffected() {
        return unitsAffected;
    }

    public void setUnitsAffected(Integer unitsAffected) {
        this.unitsAffected = unitsAffected;
    }

    public String getNotifiedDate() {
        return notifiedDate;
    }

    public void setNotifiedDate(String notifiedDate) {
        this.notifiedDate = notifiedDate;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDefectSummary() {
        return defectSummary;
    }

    public void setDefectSummary(String defectSummary) {
        this.defectSummary = defectSummary;
    }

    public String getFmvss() {
        return fmvss;
    }

    public void setFmvss(String fmvss) {
        this.fmvss = fmvss;
    }

    public String getConsequenceSummary() {
        return consequenceSummary;
    }

    public void setConsequenceSummary(String consequenceSummary) {
        this.consequenceSummary = consequenceSummary;
    }

    public String getCorrectiveSummary() {
        return correctiveSummary;
    }

    public void setCorrectiveSummary(String correctiveSummary) {
        this.correctiveSummary = correctiveSummary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

}