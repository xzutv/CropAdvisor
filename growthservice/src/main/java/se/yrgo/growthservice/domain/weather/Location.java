package se.yrgo.growthservice.domain.weather;

public class Location {
    private int id;

    private String city;
    private String country;

    private Weather weather;

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Weather getWeather() {
        return weather;
    }
}

