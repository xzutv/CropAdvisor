package se.yrgo.cropservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.cropservice.data.CropRepository;
import se.yrgo.cropservice.entities.Crop;
import se.yrgo.cropservice.entities.enums.PlantType;
import se.yrgo.cropservice.entities.enums.SoilType;

import java.util.List;
import java.util.Optional;

@Service
public class CropService {

    private final CropRepository cropRepository;

    public CropService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    // Complex, get back later
//    public List<Crop> findCropsCompatibleWithSoil(SoilType soilType) {
//
//    }

    public Optional<Crop> findCropByName(String name) {
        return cropRepository.findByName(name);
    }

    public List<Crop> findByPlantType(PlantType plantType) {
        return cropRepository.findByPlantType(plantType);
    }

    public Optional<Crop> findByLatinName(String latinName) {
        return cropRepository.findByLatinName(latinName);
    }


    public Crop createCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    public Crop updateCrop(Crop crop) {
        return cropRepository.save(crop);
    }

    public void deleteCrop(Crop crop) {
        cropRepository.delete(crop);
    }

    public List<Crop> findAll() {
        return cropRepository.findAll();
    }




}
