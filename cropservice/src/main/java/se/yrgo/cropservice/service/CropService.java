package se.yrgo.cropservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.cropservice.data.CropRepository;
import se.yrgo.cropservice.entities.Crop;
import se.yrgo.cropservice.entities.EnvironmentProfile;
import se.yrgo.cropservice.entities.GrowthRequirements;
import se.yrgo.cropservice.entities.enums.PlantType;
import se.yrgo.cropservice.entities.enums.SoilType;
import se.yrgo.cropservice.entities.enums.SunExposure;
import se.yrgo.cropservice.exceptions.CropNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CropService {

    private final CropRepository cropRepository;
    private final EnvironmentProfileService profileService;
    private final GrowthRequirementService requirmentService;

    @Autowired
    public CropService(CropRepository cropRepository, EnvironmentProfileService profileService, GrowthRequirementService requirmentService) {
        this.cropRepository = cropRepository;
        this.profileService = profileService;
        this.requirmentService = requirmentService;
    }

    public boolean checkHeatRisk(Long cropId, double forecastMaxTemp) {
        Crop crop = getCropById(cropId)
                .orElseThrow(() -> new CropNotFoundException("Crop not found with id: " + cropId));
        return requirmentService.isHeatRisk(crop.getRequirements(), forecastMaxTemp);
    }

    public Optional<Crop> getCropById(Long id) {
        return cropRepository.findById(id);
    }

    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    public Optional<Crop> findCropByName(String name) {
        return cropRepository.findByName(name);
    }

    public List<Crop> findByPlantType(PlantType plantType) {
        return cropRepository.findByType(plantType);
    }

    public Crop createCompleteCropWithProfile(String name,
                                              String latinName,
                                              PlantType plantType,
                                              EnvironmentProfile profile,
                                              GrowthRequirements requirements) {

        Crop crop = new Crop();
        crop.setName(name);
        crop.setLatinName(latinName);
        crop.setType(plantType);
        crop.setEnvironmentProfile(profile);
        crop.setRequirements(requirements);

        return cropRepository.save(crop);
    }

    public Crop createProfileWithDefaults(String name,
                                          String latinName,
                                          PlantType plantType,
                                          SoilType soilType,
                                          SunExposure exposure) {
        EnvironmentProfile profile = profileService.createDefaultProfile(soilType, exposure);
        boolean isTropical = (plantType == PlantType.TROPICAL || plantType == PlantType.FRUIT);
        GrowthRequirements requirements = requirmentService.createDefaultRequirements(isTropical);

        return createCompleteCropWithProfile(name, latinName, plantType, profile, requirements);
    }


    public Crop updateCropBasicInfo(Long id, String name, String latinName, PlantType plantType) {
        Crop crop = getCropById(id)
                .orElseThrow(() -> new CropNotFoundException("Crop not found with id: " + id));

        if (name != null) {crop.setName(name);}
        if (latinName != null) {crop.setLatinName(latinName);}
        if (plantType != null) {crop.setType(plantType);}

        return cropRepository.save(crop);
    }

    public Crop updateEnvironmentProfile(Long cropId, EnvironmentProfile update) {
        Crop crop = getCropById(cropId)
                .orElseThrow(() -> new CropNotFoundException("Crop not found with id: " + cropId));

        if (crop.getEnvironmentProfile() == null) {
            crop.setEnvironmentProfile(update);
        } else {
            profileService.updateProfile(crop.getEnvironmentProfile(), update);
        }
        return cropRepository.save(crop);
    }



}
