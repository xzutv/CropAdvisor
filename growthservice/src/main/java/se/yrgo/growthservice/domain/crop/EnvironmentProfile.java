package se.yrgo.growthservice.domain.crop;

import se.yrgo.growthservice.domain.crop.enums.SoilType;
import se.yrgo.growthservice.domain.crop.enums.SunExposure;

public class EnvironmentProfile {
    private Long id;


    private SoilType soilType;

    private SunExposure sunExposure;

    private double soilPhMin;
    private double soilPhMax;

    private String plantingSeason;
    private String harvestSeason;

    public EnvironmentProfile() {}

    public EnvironmentProfile(Long id, SoilType soilType, SunExposure sunExposure, double soilPhMin, double soilPhMax, String plantingSeason, String harvestSeason) {
        this.id = id;
        this.soilType = soilType;
        this.sunExposure = sunExposure;
        this.soilPhMin = soilPhMin;
        this.soilPhMax = soilPhMax;
        this.plantingSeason = plantingSeason;
        this.harvestSeason = harvestSeason;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
