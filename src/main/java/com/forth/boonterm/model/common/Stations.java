package com.forth.boonterm.model.common;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Stations implements Serializable {

    @SerializedName("Status")
    private String status;

    @SerializedName("StateA")
    private String stateA;

    @SerializedName("HardwareModel")
    private String hardwareModel;

    @SerializedName("StateB")
    private String stateB;

    @SerializedName("PlaceType")
    private String placeType;

    @SerializedName("HardwareType")
    private String hardwareType;

    @SerializedName("StationNumber")
    private String stationNumber;

    @SerializedName("HardwareDesc")
    private String hardwareDesc;

    @SerializedName("Address2")
    private String address2;

    @SerializedName("Latitude")
    private String latitude;

    @SerializedName("Address1")
    private String address1;

    @SerializedName("Longitude")
    private String longitude;

    public String getStatus() {
        return status;
    }

    public String getStateA() {
        return stateA;
    }

    public String getHardwareModel() {
        return hardwareModel;
    }

    public String getStateB() {
        return stateB;
    }

    public String getPlaceType() {
        return placeType;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public String getHardwareDesc() {
        return hardwareDesc;
    }

    public String getAddress2() {
        return address2;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getAddress1() {
        return address1;
    }

    public String getLongitude() {
        return longitude;
    }
}