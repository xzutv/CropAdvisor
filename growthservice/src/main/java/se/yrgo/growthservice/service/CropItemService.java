package se.yrgo.growthservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.growthservice.client.WeatherClient;
import se.yrgo.growthservice.data.CropItemRepository;
import se.yrgo.growthservice.domain.crop.Crop;
import se.yrgo.growthservice.domain.weather.Location;
import se.yrgo.growthservice.entities.CropItem;

import java.util.List;
import java.util.Optional;

@Service
public class CropItemService {

    @Autowired
    private CropItemRepository cropItemRepository;

    @Autowired
    private CropService cropService;

    @Autowired
    private WeatherClient weatherClient;

    public List<Location> getAllLocations() {
        return weatherClient.getAllLocations();
    }

    public CropItem createCropItem(Long cropId, String city, String country) {
        validateLocationExists(city, country);

        Optional<CropItem> existing = cropItemRepository
                .findByCropIdAndCityAndCountry(cropId, city, country);

        if (existing.isPresent()) {
            throw new IllegalArgumentException(
                    "A CropItem with this crop and location already exists: id=" + existing.get().getId()
            );
        }

        CropItem newItem = new CropItem(cropId, city, country);
        return cropItemRepository.save(newItem);
    }

    public List<CropItem> getAllCropItems() {
        return cropItemRepository.findAll();
    }

    public Optional<CropItem> findById(Long id) {
        return cropItemRepository.findById(id);
    }

    public CropItem updateCropItem(Long id, Long cropId, String city, String country) {
        validateLocationExists(city, country);

        CropItem item = cropItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CropItem not found"));

        boolean changed = false;
        if (!item.getCity().equals(city)) {
            item.setCity(city);
            changed = true;
        }
        if (!item.getCountry().equals(country)) {
            item.setCountry(country);
            changed = true;
        }
        if (!item.getCropId().equals(cropId)) {
            item.setCropId(cropId);
            changed = true;
        }

        if (!changed) {
            throw new IllegalArgumentException("No changes applied, CropItem already has these values");
        }

        return cropItemRepository.save(item);
    }

    public void deleteCropItem(Long id) {
        cropItemRepository.deleteById(id);
    }

    public Location getLocationForItem(CropItem item) {
        return weatherClient.getAllLocations().stream()
                .filter(loc -> loc.getCity().equals(item.getCity()) &&
                        loc.getCountry().equals(item.getCountry()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Location not found for city: " + item.getCity() + ", country: " + item.getCountry()
                ));
    }

    public Optional<CropItem> getByCropId(Long cropId) {
        return cropItemRepository.findById(cropId);
    }

    private void validateLocationExists(String city, String country) {
        boolean exists = weatherClient.getAllLocations().stream()
                .anyMatch(loc -> loc.getCity().equals(city) && loc.getCountry().equals(country));
        if (!exists) {
            throw new IllegalArgumentException("No location found for city: " + city + ", country: " + country);
        }
    }
}
