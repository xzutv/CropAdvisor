package se.yrgo.growthservice.domain.crop;

import jakarta.persistence.*;
import se.yrgo.growthservice.domain.crop.enums.PlantType;

public class Crop {

    private Long id;

    private String name;
    private String latinName;

    private PlantType type;

    private GrowthRequirements requirements;

    private EnvironmentProfile environmentProfile;

    public Crop() {}

    public Crop(Long id, String name, String latinName, PlantType type, GrowthRequirements requirements, EnvironmentProfile environmentProfile) {
        this.id = id;
        this.name = name;
        this.latinName = latinName;
        this.type = type;
        this.requirements = requirements;
        this.environmentProfile = environmentProfile;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public GrowthRequirements getRequirements() {
        return requirements;
    }

    public void setRequirements(GrowthRequirements requirements) {
        this.requirements = requirements;
    }

    public EnvironmentProfile getEnvironmentProfile() {
        return environmentProfile;
    }

    public void setEnvironmentProfile(EnvironmentProfile environmentProfile) {
        this.environmentProfile = environmentProfile;
    }
}
