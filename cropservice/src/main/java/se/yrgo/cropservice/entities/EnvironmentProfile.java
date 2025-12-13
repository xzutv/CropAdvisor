package se.yrgo.cropservice.entities;

import jakarta.persistence.*;
import se.yrgo.cropservice.entities.enums.SoilType;
import se.yrgo.cropservice.entities.enums.SunExposure;

@Entity
public class EnvironmentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "environmentProfile")
    private Crop crop;

    @Enumerated(EnumType.STRING)
    private SoilType soilType;
    
    @Enumerated(EnumType.STRING)
    private SunExposure sunExposure;

    private double soilPhMin;
    private double soilPhMax;

    private String plantingSeason;
    private String harvestSeason;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Crop getCrop() {
        return crop;
    }

    public SoilType getSoilType() {
        return soilType;
    }

    public void setSoilType(SoilType soilType) {
        this.soilType = soilType;
    }

    public SunExposure getSunExposure() {
        return sunExposure;
    }

    public void setSunExposure(SunExposure sunExposure) {
        this.sunExposure = sunExposure;
    }

    public double getSoilPhMin() {
        return soilPhMin;
    }

    public void setSoilPhMin(double soilPhMin) {
        this.soilPhMin = soilPhMin;
    }

    public double getSoilPhMax() {
        return soilPhMax;
    }

    public void setSoilPhMax(double soilPhMax) {
        this.soilPhMax = soilPhMax;
    }

    public String getPlantingSeason() {
        return plantingSeason;
    }

    public void setPlantingSeason(String plantingSeason) {
        this.plantingSeason = plantingSeason;
    }

    public String getHarvestSeason() {
        return harvestSeason;
    }

    public void setHarvestSeason(String harvestSeason) {
        this.harvestSeason = harvestSeason;
    }
}
