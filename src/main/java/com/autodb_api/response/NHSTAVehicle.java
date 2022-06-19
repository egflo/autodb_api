package com.autodb_api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class NHSTASafteyRatings {
    public Integer Count;
    public String Message;
    public ArrayList<NHSTAVehicle> Results;
}

class NHSTASafetyRatingResult {
    public String VehicleDescription;
    public Integer VehicleId;
}

public class NHSTAVehicle{
    @SerializedName("VehiclePicture")
    public String vehiclePicture;
    @SerializedName("OverallRating")
    public String overallRating;
    @SerializedName("OverallFrontCrashRating")
    public String overallFrontCrashRating;
    @SerializedName("FrontCrashDriversideRating")
    public String frontCrashDriversideRating;
    @SerializedName("FrontCrashPassengersideRating")
    public String frontCrashPassengersideRating;
    @SerializedName("FrontCrashPicture")
    public String frontCrashPicture;
    @SerializedName("FrontCrashVideo")
    public String frontCrashVideo;
    @SerializedName("OverallSideCrashRating")
    public String overallSideCrashRating;
    @SerializedName("SideCrashDriversideRating")
    public String sideCrashDriversideRating;
    @SerializedName("SideCrashPassengersideRating")
    public String sideCrashPassengersideRating;
    @SerializedName("SideCrashPicture")
    public String sideCrashPicture;
    @SerializedName("SideCrashVideo")
    public String sideCrashVideo;
    @SerializedName("combinedSideBarrierAndPoleRating-Front")
    public String combinedSideBarrierAndPoleRatingFront;
    @SerializedName("combinedSideBarrierAndPoleRating-Rear")
    public String combinedSideBarrierAndPoleRatingRear;
    @SerializedName("sideBarrierRating-Overall")
    public String sideBarrierRatingOverall;
    @SerializedName("RolloverRating")
    public String rolloverRating;
    @SerializedName("RolloverRating2")
    public String rolloverRating2;
    @SerializedName("RolloverPossibility")
    public double rolloverPossibility;
    @SerializedName("RolloverPossibility2")
    public double rolloverPossibility2;
    @SerializedName("dynamicTipResult")
    public String dynamicTipResult;
    @SerializedName("SidePoleCrashRating")
    public String sidePoleCrashRating;
    @SerializedName("NHTSAElectronicStabilityControl")
    public String nHTSAElectronicStabilityControl;
    @SerializedName("NHTSAForwardCollisionWarning")
    public String nHTSAForwardCollisionWarning;
    @SerializedName("NHTSALaneDepartureWarning")
    public String nHTSALaneDepartureWarning;
    @SerializedName("ComplaintsCount")
    public int complaintsCount;
    @SerializedName("RecallsCount")
    public int recallsCount;
    @SerializedName("InvestigationCount")
    public int investigationCount;
    @SerializedName("ModelYear")
    public int modelYear;
    @SerializedName("Make")
    public String make;
    @SerializedName("Model")
    public String model;
    @SerializedName("VehicleDescription")
    public String vehicleDescription;
    @SerializedName("VehicleId")
    public int vehicleId;
}
