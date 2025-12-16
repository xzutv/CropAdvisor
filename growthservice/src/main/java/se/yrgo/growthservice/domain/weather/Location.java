package se.yrgo.growthservice.domain.weather;


public class Location {

    private Long id;

    private LocationId locationId;

    public Location() {
        this.locationId = new LocationId();
    }

    public String getCity() {
        return locationId.getCity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        locationId.setCity(city);
    }

    public String getCountry() {
        return locationId.getCountry();
    }

    public void setCountry(String country) {
        locationId.setCountry(country);
    }

    public LocationId getLocationId() {
        return locationId;
    }
}
