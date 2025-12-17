package se.yrgo.frontend.model;

public class Crop {
    private Long id;
    private String name;
    private String latinName;
    private String type;
    private GrowthRequirements requirements;
    private EnvironmentProfile environmentProfile;

    public Crop() {
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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