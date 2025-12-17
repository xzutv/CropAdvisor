package se.yrgo.cropservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.yrgo.cropservice.data.CropRepository;
import se.yrgo.cropservice.entities.Crop;
import se.yrgo.cropservice.entities.EnvironmentProfile;
import se.yrgo.cropservice.entities.GrowthRequirements;
import se.yrgo.cropservice.entities.enums.PlantType;
import se.yrgo.cropservice.entities.enums.SoilType;
import se.yrgo.cropservice.entities.enums.SunExposure;

@Configuration
public class CropDataInitializer {

    @Bean
    CommandLineRunner initDatabase(CropRepository cropRepository) {
        return args -> {
            int addedCount = 0;

            // 1. Tomat
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Tomat",
                "Solanum lycopersicum",
                PlantType.VEGETABLE,
                createGrowthRequirements(18.0, 27.0, 2.5, true, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.FULL_SUN, 6.0, 6.8, "Vår", "Sommar-Höst")
            ));

            // 2. Morot
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Morot",
                "Daucus carota",
                PlantType.ROOT_VEGETABLE,
                createGrowthRequirements(10.0, 24.0, 2.0, false, false),
                createEnvironmentProfile(SoilType.SANDY, SunExposure.FULL_SUN, 6.0, 7.0, "Vår", "Höst")
            ));

            // 3. Basilika
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Basilika",
                "Ocimum basilicum",
                PlantType.HERB,
                createGrowthRequirements(20.0, 30.0, 1.5, true, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.FULL_SUN, 6.0, 7.5, "Vår", "Sommar")
            ));

            // 4. Jordgubbe
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Jordgubbe",
                "Fragaria × ananassa",
                PlantType.FRUIT,
                createGrowthRequirements(15.0, 26.0, 2.0, true, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.FULL_SUN, 5.5, 6.5, "Vår", "Sommar")
            ));

            // 5. Kaktus
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Guldbollskaktus",
                "Echinocactus grusonii",
                PlantType.CACTUS,
                createGrowthRequirements(20.0, 35.0, 0.5, true, false),
                createEnvironmentProfile(SoilType.SANDY, SunExposure.FULL_SUN, 6.0, 7.5, "Vår", "Sommar")
            ));

            // 6. Potatis
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Potatis",
                "Solanum tuberosum",
                PlantType.ROOT_VEGETABLE,
                createGrowthRequirements(15.0, 20.0, 2.5, true, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.FULL_SUN, 4.8, 6.5, "Vår", "Sommar-Höst")
            ));

            // 7. Ärt
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Ärt",
                "Pisum sativum",
                PlantType.LEGUME,
                createGrowthRequirements(13.0, 18.0, 2.0, false, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.FULL_SUN, 6.0, 7.5, "Vår", "Sommar")
            ));

            // 8. Vete
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Vete",
                "Triticum aestivum",
                PlantType.GRAIN,
                createGrowthRequirements(12.0, 25.0, 2.0, false, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.FULL_SUN, 6.0, 7.0, "Höst", "Sommar")
            ));

            // 9. Lavendel
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Lavendel",
                "Lavandula angustifolia",
                PlantType.HERB,
                createGrowthRequirements(15.0, 30.0, 1.0, false, false),
                createEnvironmentProfile(SoilType.CHALKY, SunExposure.FULL_SUN, 6.5, 8.0, "Vår", "Sommar")
            ));

            // 10. Blåbär
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Blåbär",
                "Vaccinium corymbosum",
                PlantType.FRUIT,
                createGrowthRequirements(16.0, 27.0, 3.0, false, false),
                createEnvironmentProfile(SoilType.PEATY, SunExposure.PART_SUN, 4.5, 5.5, "Vår", "Sommar")
            ));

            // 11. Monstera
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Monstera",
                "Monstera deliciosa",
                PlantType.HOUSEPLANT,
                createGrowthRequirements(18.0, 27.0, 1.5, true, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.PARTIAL_SHADE, 5.5, 7.0, "Vår", "Sommar")
            ));

            // 12. Paprika
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Paprika",
                "Capsicum annuum",
                PlantType.VEGETABLE,
                createGrowthRequirements(21.0, 29.0, 2.5, true, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.FULL_SUN, 6.0, 7.0, "Vår", "Sommar-Höst")
            ));

            // 13. Ek
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Ek",
                "Quercus robur",
                PlantType.TREE,
                createGrowthRequirements(5.0, 25.0, 3.0, false, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.FULL_SUN, 5.5, 7.5, "Höst", "N/A")
            ));

            // 14. Lök
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Lök",
                "Allium cepa",
                PlantType.ROOT_VEGETABLE,
                createGrowthRequirements(12.0, 24.0, 1.5, true, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.FULL_SUN, 6.0, 7.0, "Vår", "Sommar-Höst")
            ));

            // 15. Timjan
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Timjan",
                "Thymus vulgaris",
                PlantType.HERB,
                createGrowthRequirements(15.0, 28.0, 1.0, false, false),
                createEnvironmentProfile(SoilType.CHALKY, SunExposure.FULL_SUN, 6.0, 8.0, "Vår", "Sommar")
            ));

            // 16. Mango
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Mango",
                "Mangifera indica",
                PlantType.TROPICAL,
                createGrowthRequirements(24.0, 30.0, 3.5, true, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.FULL_SUN, 5.5, 7.5, "Vår", "Sommar")
            ));

            // 17. Sallad
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Sallad",
                "Lactuca sativa",
                PlantType.VEGETABLE,
                createGrowthRequirements(7.0, 18.0, 2.0, false, true),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.PART_SUN, 6.0, 7.0, "Vår-Höst", "Vår-Höst")
            ));

            // 18. Bönor
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Bönor",
                "Phaseolus vulgaris",
                PlantType.LEGUME,
                createGrowthRequirements(18.0, 30.0, 2.5, true, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.FULL_SUN, 6.0, 7.5, "Vår", "Sommar-Höst")
            ));

            // 19. Gräsmatta
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Gräsmatta",
                "Festuca rubra",
                PlantType.GRASS,
                createGrowthRequirements(10.0, 25.0, 2.5, false, false),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.FULL_SUN, 6.0, 7.5, "Vår-Höst", "N/A")
            ));

            // 20. Spenat
            addedCount += saveIfNotExists(cropRepository, createCrop(
                "Spenat",
                "Spinacia oleracea",
                PlantType.VEGETABLE,
                createGrowthRequirements(10.0, 20.0, 2.0, false, true),
                createEnvironmentProfile(SoilType.LOAMY, SunExposure.PART_SUN, 6.5, 7.5, "Vår-Höst", "Vår-Höst")
            ));

            if (addedCount > 0) {
                System.out.println("Lade till " + addedCount + " nya grödor i databasen");
            } else {
                System.out.println("Alla grödor finns redan i databasen");
            }
        };
    }

    private int saveIfNotExists(CropRepository repository, Crop crop) {
        if (repository.findByName(crop.getName()).isEmpty()) {
            repository.save(crop);
            return 1;
        }
        return 0;
    }

    private Crop createCrop(String name, String latinName, PlantType type,
                           GrowthRequirements requirements, EnvironmentProfile profile) {
        Crop crop = new Crop();
        crop.setName(name);
        crop.setLatinName(latinName);
        crop.setType(type);
        crop.setRequirements(requirements);
        crop.setEnvironmentProfile(profile);
        return crop;
    }

    private GrowthRequirements createGrowthRequirements(double tempMin, double tempMax,
                                                       double waterPerWeek, boolean frostSensitive,
                                                       boolean heatSensitive) {
        GrowthRequirements req = new GrowthRequirements();
        req.setOptimalTempMin(tempMin);
        req.setOptimalTempMax(tempMax);
        req.setWaterLitersPerWeek(waterPerWeek);
        req.setFrostSensitive(frostSensitive);
        req.setHeatSensitive(heatSensitive);
        return req;
    }

    private EnvironmentProfile createEnvironmentProfile(SoilType soilType, SunExposure sunExposure,
                                                        double phMin, double phMax,
                                                        String plantingSeason, String harvestSeason) {
        EnvironmentProfile profile = new EnvironmentProfile();
        profile.setSoilType(soilType);
        profile.setSunExposure(sunExposure);
        profile.setSoilPhMin(phMin);
        profile.setSoilPhMax(phMax);
        profile.setPlantingSeason(plantingSeason);
        profile.setHarvestSeason(harvestSeason);
        return profile;
    }
}