package se.yrgo.cropservice.dto;

import se.yrgo.cropservice.entities.enums.SoilType;
import se.yrgo.cropservice.entities.enums.SunExposure;

public class EnvironmentProfileDTO {

    private Long id;
    private SoilType soilType;
    private SunExposure sunExposure;
    private double soilPhMin;
    private double soilPhMax;
    private String plantingSeason;
    private String harvestSeason;

    public EnvironmentProfileDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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