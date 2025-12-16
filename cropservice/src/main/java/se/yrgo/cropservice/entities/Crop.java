package se.yrgo.cropservice.entities;

import jakarta.persistence.*;
import se.yrgo.cropservice.entities.enums.PlantType;

@Entity
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String latinName;

    @Enumerated(EnumType.STRING)
    private PlantType type;

    @Embedded
    private GrowthRequirements requirements;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
    orphanRemoval = true)
    @JoinColumn(name = "environment_profile_id")
    private EnvironmentProfile environmentProfile;

    public Crop() {}


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
