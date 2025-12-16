package se.yrgo.growthservice.entities;

import jakarta.persistence.*;
import se.yrgo.growthservice.domain.weather.LocationId;

@Entity
public class CropItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String city;
    private String country;

    private Long cropId;

    public CropItem() {
    }

    public CropItem(String city, String country, Long cropId) {
        this.city = city;
        this.country = country;
        this.cropId = cropId;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Long getCropId() {
        return cropId;
    }
}
