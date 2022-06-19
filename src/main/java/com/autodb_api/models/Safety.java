package com.autodb_api.models;

import javax.persistence.*;


@Entity
@Table(name = "safety")
public class Safety {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String vehiclePicture;
    private String overallRating;
    private String overallFrontCrashRating;
    private String frontCrashDriversideRating;
    private String frontCrashPassengersideRating;
    private String frontCrashPicture;
    private String frontCrashVideo;
    private String overallSideCrashRating;
    private String sideCrashDriversideRating;
    private String sideCrashPassengersideRating;
    private String sideCrashPicture;
    private String sideCrashVideo;
    private String combinedSideBarrierAndPoleRatingFront;
    private String combinedSideBarrierAndPoleRatingRear;
    private String sideBarrierRatingOverall;
    private String rolloverRating;
    private String rolloverRating2;
    private Double rolloverPossibility;
    private Double rolloverPossibility2;
    private String dynamicTipResult;
    private String sidePoleCrashRating;
    private String nHTSAElectronicStabilityControl;
    private String nHTSAForwardCollisionWarning;
    private String nHTSALaneDepartureWarning;
    private Integer complaintsCount;
    private Integer recallsCount;
    private Integer investigationCount;
    private Integer modelYear;
    private String make;
    private String model;
    private String vehicleDescription;
    private Integer vehicleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehiclePicture() {
        return vehiclePicture;
    }
    public void setVehiclePicture(String vehiclePicture) {
        this.vehiclePicture = vehiclePicture;
    }
    public String getOverallRating() {
        return overallRating;
    }
    public void setOverallRating(String overallRating) {
        this.overallRating = overallRating;
    }
    public String getOverallFrontCrashRating() {
        return overallFrontCrashRating;
    }
    public void setOverallFrontCrashRating(String overallFrontCrashRating) {
        this.overallFrontCrashRating = overallFrontCrashRating;
    }
    public String getFrontCrashDriversideRating() {
        return frontCrashDriversideRating;
    }
    public void setFrontCrashDriversideRating(String frontCrashDriversideRating) {
        this.frontCrashDriversideRating = frontCrashDriversideRating;
    }
    public String getFrontCrashPassengersideRating() {
        return frontCrashPassengersideRating;
    }
    public void setFrontCrashPassengersideRating(String frontCrashPassengersideRating) {
        this.frontCrashPassengersideRating = frontCrashPassengersideRating;
    }
    public String getFrontCrashPicture() {
        return frontCrashPicture;
    }
    public void setFrontCrashPicture(String frontCrashPicture) {
        this.frontCrashPicture = frontCrashPicture;
    }
    public String getFrontCrashVideo() {
        return frontCrashVideo;
    }
    public void setFrontCrashVideo(String frontCrashVideo) {
        this.frontCrashVideo = frontCrashVideo;
    }
    public String getOverallSideCrashRating() {
        return overallSideCrashRating;
    }
    public void setOverallSideCrashRating(String overallSideCrashRating) {
        this.overallSideCrashRating = overallSideCrashRating;
    }
    public String getSideCrashDriversideRating() {
        return sideCrashDriversideRating;
    }
    public void setSideCrashDriversideRating(String sideCrashDriversideRating) {
        this.sideCrashDriversideRating = sideCrashDriversideRating;
    }
    public String getSideCrashPassengersideRating() {
        return sideCrashPassengersideRating;
    }
    public void setSideCrashPassengersideRating(String sideCrashPassengersideRating) {
        this.sideCrashPassengersideRating = sideCrashPassengersideRating;
    }
    public String getSideCrashPicture() {
        return sideCrashPicture;
    }
    public void setSideCrashPicture(String sideCrashPicture) {
        this.sideCrashPicture = sideCrashPicture;
    }
    public String getSideCrashVideo() {
        return sideCrashVideo;
    }
    public void setSideCrashVideo(String sideCrashVideo) {
        this.sideCrashVideo = sideCrashVideo;
    }
    public String getCombinedSideBarrierAndPoleRatingFront() {
        return combinedSideBarrierAndPoleRatingFront;
    }
    public void setCombinedSideBarrierAndPoleRatingFront(String combinedSideBarrierAndPoleRatingFront) {
        this.combinedSideBarrierAndPoleRatingFront = combinedSideBarrierAndPoleRatingFront;
    }
    public String getCombinedSideBarrierAndPoleRatingRear() {
        return combinedSideBarrierAndPoleRatingRear;
    }
    public void setCombinedSideBarrierAndPoleRatingRear(String combinedSideBarrierAndPoleRatingRear) {
        this.combinedSideBarrierAndPoleRatingRear = combinedSideBarrierAndPoleRatingRear;
    }
    public String getSideBarrierRatingOverall() {
        return sideBarrierRatingOverall;
    }
    public void setSideBarrierRatingOverall(String sideBarrierRatingOverall) {
        this.sideBarrierRatingOverall = sideBarrierRatingOverall;
    }
    public String getRolloverRating() {
        return rolloverRating;
    }
    public void setRolloverRating(String rolloverRating) {
        this.rolloverRating = rolloverRating;
    }
    public String getRolloverRating2() {
        return rolloverRating2;
    }
    public void setRolloverRating2(String rolloverRating2) {
        this.rolloverRating2 = rolloverRating2;
    }
    public Double getRolloverPossibility() {
        return rolloverPossibility;
    }
    public void setRolloverPossibility(Double rolloverPossibility) {
        this.rolloverPossibility = rolloverPossibility;
    }
    public Double getRolloverPossibility2() {
        return rolloverPossibility2;
    }
    public void setRolloverPossibility2(Double rolloverPossibility2) {
        this.rolloverPossibility2 = rolloverPossibility2;
    }
    public String getDynamicTipResult() {
        return dynamicTipResult;
    }
    public void setDynamicTipResult(String dynamicTipResult) {
        this.dynamicTipResult = dynamicTipResult;
    }
    public String getSidePoleCrashRating() {
        return sidePoleCrashRating;
    }
    public void setSidePoleCrashRating(String sidePoleCrashRating) {
        this.sidePoleCrashRating = sidePoleCrashRating;
    }
    public String getNHTSAElectronicStabilityControl() {
        return nHTSAElectronicStabilityControl;
    }
    public void setNHTSAElectronicStabilityControl(String nHTSAElectronicStabilityControl) {
        this.nHTSAElectronicStabilityControl = nHTSAElectronicStabilityControl;
    }
    public String getNHTSAForwardCollisionWarning() {
        return nHTSAForwardCollisionWarning;
    }
    public void setNHTSAForwardCollisionWarning(String nHTSAForwardCollisionWarning) {
        this.nHTSAForwardCollisionWarning = nHTSAForwardCollisionWarning;
    }
    public String getNHTSALaneDepartureWarning() {
        return nHTSALaneDepartureWarning;
    }
    public void setNHTSALaneDepartureWarning(String nHTSALaneDepartureWarning) {
        this.nHTSALaneDepartureWarning = nHTSALaneDepartureWarning;
    }
    public Integer getComplaintsCount() {
        return complaintsCount;
    }
    public void setComplaintsCount(Integer complaintsCount) {
        this.complaintsCount = complaintsCount;
    }
    public Integer getRecallsCount() {
        return recallsCount;
    }
    public void setRecallsCount(Integer recallsCount) {
        this.recallsCount = recallsCount;
    }
    public Integer getInvestigationCount() {
        return investigationCount;
    }
    public void setInvestigationCount(Integer investigationCount) {
        this.investigationCount = investigationCount;
    }
    public Integer getModelYear() {
        return modelYear;
    }
    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
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
    public String getVehicleDescription() {
        return vehicleDescription;
    }
    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }
    public Integer getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
}