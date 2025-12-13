package se.yrgo.growthservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.growthservice.dao.CreateCropRequest;
import se.yrgo.growthservice.domain.crop.Crop;
import se.yrgo.growthservice.domain.crop.EnvironmentProfile;
import se.yrgo.growthservice.domain.crop.GrowthRequirements;
import se.yrgo.growthservice.domain.crop.enums.PlantType;
import se.yrgo.growthservice.domain.crop.enums.SoilType;
import se.yrgo.growthservice.domain.crop.enums.SunExposure;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockCropService {

    private long idSequence = 1L;

    private final List<Crop> crops = new ArrayList<>();

    public MockCropService() {
        GrowthRequirements requirements =
                new GrowthRequirements(1.1, 1.3, 5.5, false, true);

        EnvironmentProfile profile =
                new EnvironmentProfile(1L,
                        SoilType.CHALKY,
                        SunExposure.FULL_SUN,
                        7.5,
                        8.5,
                        "Spring",
                        "Autumn");

        for (int i = 0; i < 5; i++) {
            crops.add(new Crop(
                    idSequence++,
                    "Test",
                    "Tesius",
                    PlantType.CACTUS,
                    requirements,
                    profile
            ));
        }
    }

    public List<Crop> getCrops() {
        return crops;
    }

    public List<Crop> searchByType(PlantType plantType) {
        return crops.stream()
                .filter(crop -> crop.getType() == plantType)
                .toList();
    }

    public Crop getCropById(Long cropId) {
        return crops.stream()
                .filter(crop -> crop.getId().equals(cropId))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Crop not found with id: " + cropId));
    }

    public Crop createCrop(Crop newCrop) {
        newCrop.setId(idSequence++);
        crops.add(newCrop);
        return newCrop;
    }

    public Crop createDefaultCrop(CreateCropRequest request) {

        boolean isTropical =
                request.getType() == PlantType.TROPICAL
                        || request.getType() == PlantType.FRUIT;

        GrowthRequirements requirements = createDefaultRequirements(isTropical);
        EnvironmentProfile profile =
                createDefaultEnvironmentProfile(
                        request.getSoilType(),
                        request.getSunExposure()
                );

        Crop crop = new Crop(
                idSequence++,
                request.getName(),
                request.getLatinName(),
                request.getType(),
                requirements,
                profile
        );

        crops.add(crop);
        return crop;
    }

    private GrowthRequirements createDefaultRequirements(boolean isTropical) {
        if (isTropical) {
            return new GrowthRequirements(
                    20,
                    35,
                    15.0,
                    true,
                    false
            );
        }

        return new GrowthRequirements(
                10,
                25,
                10.0,
                false,
                false
        );
    }

    private EnvironmentProfile createDefaultEnvironmentProfile(
            SoilType soilType,
            SunExposure sunExposure
    ) {
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
            case SILTY, PEATY -> {
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

        profile.setPlantingSeason("Spring");
        profile.setHarvestSeason("Autumn");

        return profile;
    }
}
