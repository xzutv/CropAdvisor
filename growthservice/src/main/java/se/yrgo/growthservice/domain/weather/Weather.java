package se.yrgo.growthservice.domain.weather;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Weather {

    private BigDecimal temp;
    private Integer humidity;
    private BigDecimal wind;
    private BigDecimal rain;
    private BigDecimal clouds;
    private LocalDate timestamp;

    private Location location;

    public Weather() {
    }

    public Weather(BigDecimal temp, Integer humidity, BigDecimal wind, BigDecimal rain, BigDecimal clouds, LocalDate timestamp, Location location) {
        this.temp = temp;
        this.humidity = humidity;
        this.wind = wind;
        this.rain = rain;
        this.clouds = clouds;
        this.timestamp = timestamp;
        this.location = location;
    }

    public BigDecimal getTemp() {
        return temp;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public BigDecimal getWind() {
        return wind;
    }

    public BigDecimal getRain() {
        return rain;
    }

    public BigDecimal getClouds() {
        return clouds;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public Location getLocation() {
        return location;
    }
}