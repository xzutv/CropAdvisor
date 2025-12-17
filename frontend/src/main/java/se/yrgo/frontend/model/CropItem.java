package se.yrgo.frontend.model;

public class CropItem {
    private Long id;
    private Long cropId;
    private String city;
    private String country;

    public CropItem() {
    }

    public CropItem(Long cropId, String city, String country) {
        this.cropId = cropId;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}