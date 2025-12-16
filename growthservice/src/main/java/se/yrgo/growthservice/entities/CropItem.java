package se.yrgo.growthservice.entities;

import jakarta.persistence.*;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"crop_id", "city", "country"})
)
public class CropItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cropId;

    private String city;
    private String country;

    public CropItem() {}

    public CropItem(Long cropId, String city, String country) {
        this.cropId = cropId;
        this.city = city;
        this.country = country;
    }

    public Long getId() { return id; }
    public Long getCropId() { return cropId; }
    public String getCity() { return city; }
    public String getCountry() { return country; }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
