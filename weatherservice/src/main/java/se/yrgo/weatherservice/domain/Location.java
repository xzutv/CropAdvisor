package se.yrgo.weatherservice.domain;

import jakarta.persistence.*;

@Entity
public class Location {
    @EmbeddedId
    private LocationId id;

    public Location() {
        this.id = new LocationId();
    }

    public String getCity() {
        return id.getCity();
    }

    public void setCity(String city) {
        id.setCity(city);
    }

    public String getCountry() {
        return id.getCountry();
    }

    public void setCountry(String country) {
        id.setCountry(country);
    }

}
