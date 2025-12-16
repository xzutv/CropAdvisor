package se.yrgo.growthservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.growthservice.client.CropClient;
import se.yrgo.growthservice.dao.CreateCropRequest;
import se.yrgo.growthservice.domain.crop.Crop;
import se.yrgo.growthservice.domain.crop.enums.PlantType;

import java.util.List;
import java.util.Optional;

@Service
public class CropService {

    private final CropClient cropClient;

    public CropService(CropClient cropClient) {
        this.cropClient = cropClient;
    }

    public List<Crop> getCrops() {
        return cropClient.getCrops();
    }

    public List<Crop> searchByType(PlantType plantType) {
        return cropClient.searchByType(plantType);
    }

    public Crop createCrop(Crop newCrop) {
        return cropClient.createCrop(newCrop);
    }

    public Crop createDefaultCrop(CreateCropRequest request) {
        return cropClient.createDefaultCrop(request);
    }

    public Crop getCropById(Long cropId) {
        return cropClient.getCropById(cropId);
    }

    public Optional<Crop> findCropByName(String name) {
        return Optional.ofNullable(cropClient.findCropByName(name));
    }
}
