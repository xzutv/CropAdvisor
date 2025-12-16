package se.yrgo.cropservice.dto;

import se.yrgo.cropservice.entities.enums.PlantType;

public class CropDTO {

    private Long id;
    private String name;
    private String latinName;
    private PlantType type;
    private GrowthRequirementsDTO requirements;
    private EnvironmentProfileDTO environmentProfile;

    public CropDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public GrowthRequirementsDTO getRequirements() {
        return requirements;
    }

    public void setRequirements(GrowthRequirementsDTO requirements) {
        this.requirements = requirements;
    }

    public EnvironmentProfileDTO getEnvironmentProfile() {
        return environmentProfile;
    }

    public void setEnvironmentProfile(EnvironmentProfileDTO environmentProfile) {
        this.environmentProfile = environmentProfile;
    }
}