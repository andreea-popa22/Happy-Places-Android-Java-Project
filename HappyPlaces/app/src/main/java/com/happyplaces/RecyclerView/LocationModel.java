package com.happyplaces.RecyclerView;

public class LocationModel {

    // variables for our course
    // name and description.
    private String locationName;
    private String locationDescription;

    // creating constructor for our variables.
    public LocationModel(String locationName, String locationDescription) {
        this.locationName = locationName;
        this.locationDescription = locationDescription;
    }

    // creating getter and setter methods.
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }
}