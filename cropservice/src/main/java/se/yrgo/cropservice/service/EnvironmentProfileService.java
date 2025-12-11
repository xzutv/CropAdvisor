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
            case LOAMY -> {
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



}
