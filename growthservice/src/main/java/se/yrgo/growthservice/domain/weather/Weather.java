package se.yrgo.growthservice.domain.weather;

import java.time.LocalDate;

public class Weather {
    private Double temp;
    private int humidity;
    private String wind;
    private Boolean rain;
    private LocalDate timestamp;

    private Location location;

    public Weather(Double temp, int humidity, String wind, Boolean rain, LocalDate timestamp, Location location) {
        this.temp = temp;
        this.humidity = humidity;
        this.wind = wind;
        this.rain = rain;
        this.timestamp = timestamp;
        this.location = location;
    }

    public Double getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getWind() {
        return wind;
    }

    public Boolean getRain() {
        return rain;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }
}