package se.yrgo.cropservice.dao;

import se.yrgo.cropservice.entities.enums.PlantType;
import se.yrgo.cropservice.entities.enums.SoilType;
import se.yrgo.cropservice.entities.enums.SunExposure;

public class CreateCropRequest {

    private String name;
    private String latinName;
    private PlantType type;
    private SoilType soilType;
    private SunExposure sunExposure;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public PlantType getType() {
        return type;
    }

    public void setType(PlantType type) {
        this.type = type;
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
}
