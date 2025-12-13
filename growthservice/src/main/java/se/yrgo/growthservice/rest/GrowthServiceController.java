package se.yrgo.growthservice.rest;

import jakarta.annotation.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.growthservice.dao.CropItemData;
import se.yrgo.growthservice.domain.crop.Crop;
import se.yrgo.growthservice.domain.crop.GrowthRequirements;
import se.yrgo.growthservice.domain.weather.Location;
import se.yrgo.growthservice.domain.weather.Weather;
import se.yrgo.growthservice.entities.CropItem;
import se.yrgo.growthservice.service.GreenhouseStorageService;
import se.yrgo.growthservice.service.MockCropService;
import se.yrgo.growthservice.service.MockWeatherService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/advice")
public class GrowthServiceController {

    private final MockWeatherService weatherService;
    private final MockCropService cropService;
    private final GreenhouseStorageService storageService;

    public GrowthServiceController(MockWeatherService weatherService, MockCropService cropService, GreenhouseStorageService storageService) {
        this.weatherService = weatherService;
        this.cropService = cropService;
        this.storageService = storageService;
    }

    @GetMapping("/")
    public GrowthResponse bajs() {
        return new GrowthResponse("");
    }

    // ?test=test   /advice/test
    @GetMapping("/test1")
    public GrowthResponse getGrowthAdvice(@RequestParam @Nullable String country, @RequestParam @Nullable String city) {
        List<Weather> test = weatherService.getAllWeather();

        return new GrowthResponse(test);
    }


    @GetMapping("/test3")
    public GrowthResponse getGrowthAdvice2(@RequestParam @Nullable String country, @RequestParam @Nullable String city) {
        List<Location> test = weatherService.getAllLocations();

        return new GrowthResponse(test);
    }

    @GetMapping("/test2")
    public GrowthResponse getTest() {
        List<Crop> crops = cropService.getCrops();
        return new GrowthResponse(crops);
    }

    @GetMapping("/test4")
    public GrowthResponse listCropItems() {
        List<CropItem> crops = storageService.findAll();
        return new GrowthResponse(mapCropItemToData(crops));
    }

    private List<CropItemData> mapCropItemToData(List<CropItem> cropItems) {
        Map<Integer, Location> locationById = weatherService.getAllLocations()
                .stream()
                .collect(Collectors.toMap(Location::getId, Function.identity()));

        Map<Long, Crop> cropById = cropService.getCrops()
                .stream()
                .collect(Collectors.toMap(Crop::getId, Function.identity()));


        return cropItems.stream()
                .map(cropItem -> {
                    Location location = locationById.get(cropItem.getLocationId());
                    Crop crop = cropById.get(cropItem.getCropId());

                    return new CropItemData(
                            crop,
                            location
                    );
                })
                .toList();
    }

    public record GrowthResponse(Object message) {
    }
}
