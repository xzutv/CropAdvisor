package se.yrgo.cropservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.cropservice.entities.EnvironmentProfile;
import se.yrgo.cropservice.entities.enums.SoilType;
import se.yrgo.cropservice.entities.enums.SunExposure;

@Service
public class EnvironmentProfileService {


    /**
     *
     * Creates a default profile based on Crop type
     *
     * @param soilType
     * @param sunExposure
     * @return profile
     */
    public EnvironmentProfile createDefaultProfile(SoilType soilType, SunExposure sunExposure) {
        EnvironmentProfile profile = new EnvironmentProfile();
        profile.setSoilType(soilType);
        profile.setSunExposure(sunExposure);

        switch (soilType) {
            case CLAY -> {
                profile.setSoilPhMin(5.5);
                profile.setSoilPhMax(7.5);
            }
            case SANDY -> {
                profile.setSoilPhMin(5.0);
                profile.setSoilPhMax(7.0);
            }
            case LOAM -> {
                profile.setSoilPhMin(6.0);
                profile.setSoilPhMax(7.0);
            }
            case SILTY -> {
                profile.setSoilPhMin(5.5);
                profile.setSoilPhMax(7.0);
            }
            case PEATY ->  {
                profile.setSoilPhMin(5.5);
                profile.setSoilPhMax(7.0);
            }
            case CHALKY -> {
                profile.setSoilPhMin(7.5);
                profile.setSoilPhMax(8.5);
            }
            default -> {
                profile.setSoilPhMin(5.5);
                profile.setSoilPhMax(7.5);
            }
        }
        return profile;
    }


    public void updateProfile(EnvironmentProfile existing, EnvironmentProfile update) {
        if (update.getSoilType() != null) {
            existing.setSoilType(update.getSoilType());
        }
        if (update.getSunExposure() != null) {
            existing.setSunExposure(update.getSunExposure());
        }
        if (update.getSoilPhMin() > 0) {
            existing.setSoilPhMin(update.getSoilPhMin());
        }
        if (update.getSoilPhMax() > 0) {
            existing.setSoilPhMax(update.getSoilPhMax());
        }
        if (update.getPlantingSeason() != null) {
            existing.setPlantingSeason(update.getPlantingSeason());
        }
        if (update.getHarvestSeason() != null) {
            existing.setHarvestSeason(update.getHarvestSeason());
        }

        validateProfile(update);
    }

    public void validateProfile(EnvironmentProfile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("EnvoirmentProfile cannot be null");
        }

        // Validate pH-values
        if (profile.getSoilPhMin() < 0 || profile.getSoilPhMin() > 14) {
            throw new IllegalArgumentException("Soil pH min must be between 0 and 14");
        }

        if (profile.getSoilPhMax() < 0 || profile.getSoilPhMax() > 14) {
            throw new IllegalArgumentException("Soil pH max must be between 0 and 14");
        }

        if (profile.getSoilPhMin() > profile.getSoilPhMax()) {
            throw new IllegalArgumentException("Soil pH min cannot be greater than max");
        }

        // Validate required fields
        if (profile.getSoilType() == null) {
            throw new IllegalArgumentException("Soil type is required");
        }

        if (profile.getSunExposure() == null) {
            throw new IllegalArgumentException("Sun exposure is required");
        }
    }

}
