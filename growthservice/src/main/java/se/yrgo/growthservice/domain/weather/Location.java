package se.yrgo.growthservice.domain.weather;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import se.yrgo.growthservice.domain.weather.LocationId;

@Entity
public class Location {

    @EmbeddedId
    private LocationId id;

    public Location() {
        this.id = new LocationId();
    }

    public Location(String city, String country) {
        this.id = new LocationId();
        this.id.setCity(city);
        this.id.setCountry(country);
    }

    public String getCity() { return id.getCity(); }
    public String getCountry() { return id.getCountry(); }
}