package se.yrgo.weatherservice.dto;

import se.yrgo.weatherservice.domain.Location;

import java.util.List;

public class LocationList {
    List<Location> locations;

    public LocationList(List<Location> locations) {
        this.locations = locations;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
