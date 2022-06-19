package com.autodb_api.models;

import javax.persistence.*;

@Entity
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "odino")
    private String odino;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "crash")
    private Boolean crash;

    @Column(name = "fail_date")
    private String failDate;

    @Column(name = "fire")
    private Boolean fire;

    @Column(name = "injured")
    private Integer injured;

    @Column(name = "deaths")
    private Integer deaths;

    @Column(name = "component_description")
    private String componentDescription;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "miles")
    private Integer miles;

    @Column(name = "occurances")
    private Integer occurances;

    @Column(name = "descriptions")
    private String descriptions;

    @Column(name = "police")
    private Boolean police;

    @Column(name = "purchase_date")
    private String purchaseDate;

    @Column(name = "orignal_owner")
    private Boolean orignalOwner;

    @Column(name = "original_equipment")
    private Boolean originalEquipment;

    @Column(name = "dealer_name")
    private String dealerName;

    @Column(name = "dealer_city")
    private String dealerCity;

    @Column(name = "dealer_state")
    private String dealerState;

    @Column(name = "dealer_zip")
    private String dealerZip;

    @Column(name = "medical_attn")
    private Boolean medicalAttn;

    @Column(name = "vin")
    private String vin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOdino() {
        return odino;
    }

    public void setOdino(String odino) {
        this.odino = odino;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public Boolean getCrash() {
        return crash;
    }

    public void setCrash(Boolean crash) {
        this.crash = crash;
    }

    public String getFailDate() {
        return failDate;
    }

    public void setFailDate(String failDate) {
        this.failDate = failDate;
    }

    public Boolean getFire() {
        return fire;
    }

    public void setFire(Boolean fire) {
        this.fire = fire;
    }

    public Integer getInjured() {
        return injured;
    }

    public void setInjured(Integer injured) {
        this.injured = injured;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getMiles() {
        return miles;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public Integer getOccurances() {
        return occurances;
    }

    public void setOccurances(Integer occurances) {
        this.occurances = occurances;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Boolean getPolice() {
        return police;
    }

    public void setPolice(Boolean police) {
        this.police = police;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Boolean getOrignalOwner() {
        return orignalOwner;
    }

    public void setOrignalOwner(Boolean orignalOwner) {
        this.orignalOwner = orignalOwner;
    }

    public Boolean getOriginalEquipment() {
        return originalEquipment;
    }

    public void setOriginalEquipment(Boolean originalEquipment) {
        this.originalEquipment = originalEquipment;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerCity() {
        return dealerCity;
    }

    public void setDealerCity(String dealerCity) {
        this.dealerCity = dealerCity;
    }

    public String getDealerState() {
        return dealerState;
    }

    public void setDealerState(String dealerState) {
        this.dealerState = dealerState;
    }

    public String getDealerZip() {
        return dealerZip;
    }

    public void setDealerZip(String dealerZip) {
        this.dealerZip = dealerZip;
    }

    public Boolean getMedicalAttn() {
        return medicalAttn;
    }

    public void setMedicalAttn(Boolean medicalAttn) {
        this.medicalAttn = medicalAttn;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

}