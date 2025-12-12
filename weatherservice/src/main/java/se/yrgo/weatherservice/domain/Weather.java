package se.yrgo.weatherservice.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private BigDecimal temp;
    private int humidity;
    private BigDecimal wind;
    private Boolean rain;
    private LocalDate timestamp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = new BigDecimal(temp).setScale(1, RoundingMode.HALF_UP);
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

    public void setWind(Double wind) {
        this.wind = new BigDecimal(wind).setScale(1, RoundingMode.HALF_UP);
    }

    public Boolean getRain() {
        return rain;
    }

    public void setRain(Boolean rain) {
        this.rain = rain;
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

    @Override
    public String toString() {
        return "Weather{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", wind='" + wind + '\'' +
                ", rain=" + rain +
                ", timestamp=" + timestamp +
                ", location=" + location +
                '}';
    }
}
