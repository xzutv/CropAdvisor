package se.yrgo.frontend.model;

public class EnvironmentProfile {
    private Long id;
    private String soilType;
    private String sunExposure;
    private double soilPhMin;
    private double soilPhMax;
    private String plantingSeason;
    private String harvestSeason;

    public EnvironmentProfile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getSunExposure() {
        return sunExposure;
    }

    public void setSunExposure(String sunExposure) {
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