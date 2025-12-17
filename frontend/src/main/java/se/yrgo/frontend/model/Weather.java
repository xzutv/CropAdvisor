package se.yrgo.frontend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Weather {
    private int id;
    private BigDecimal temp;
    private int humidity;
    private BigDecimal wind;
    private BigDecimal rain;
    private BigDecimal clouds;
    private LocalDate timestamp;
    private Location location;

    public Weather() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getWind() {
        return wind;
    }

    public void setWind(BigDecimal wind) {
        this.wind = wind;
    }

    public BigDecimal getRain() {
        return rain;
    }

    public void setRain(BigDecimal rain) {
        this.rain = rain;
    }

    public BigDecimal getClouds() {
        return clouds;
    }

    public void setClouds(BigDecimal clouds) {
        this.clouds = clouds;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}