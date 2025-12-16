package se.yrgo.growthservice.rest;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.growthservice.dao.CropItemData;
import se.yrgo.growthservice.dao.LocalWeatherData;
import se.yrgo.growthservice.domain.advice.Advice;
import se.yrgo.growthservice.domain.crop.Crop;
import se.yrgo.growthservice.domain.weather.Location;
import se.yrgo.growthservice.domain.weather.LocationId;
import se.yrgo.growthservice.domain.weather.Weather;
import se.yrgo.growthservice.entities.CropItem;
import se.yrgo.growthservice.service.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/advice")
public class GrowthServiceController {

    private final WeatherService weatherService;
    private final CropService cropService;
    private final StorageService storageService;
    private final AdviceService adviceService;

    public GrowthServiceController(WeatherService weatherService, CropService cropService, StorageService storageService, AdviceService adviceService) {
        this.weatherService = weatherService;
        this.cropService = cropService;
        this.storageService = storageService;
        this.adviceService = adviceService;
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

    @GetMapping("/local")
    public GrowthResponse getLocalWeather(@RequestParam @Nullable String city, @RequestParam @Nullable String country) {
        if (city == null || country == null) {
            throw new IllegalArgumentException("city and country are required");
        }
        LocalWeatherData localWeatherData = new LocalWeatherData(city, country);
        List<Weather> local = weatherService.getLocalWeather(localWeatherData);

        return new GrowthResponse(local);
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

    @GetMapping
    public List<List<Advice>> getAllAdvices() {
        return adviceService.getAllAdvices();
    }

    @GetMapping("/{itemId}")
    public List<Advice> getAdviceForItem(@PathVariable Long itemId) {
        CropItem item = storageService.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CropItem not found"));
        return adviceService.getAdvicesForItem(item);
    }


    private List<CropItemData> mapCropItemToData(List<CropItem> cropItems) {

        Map<LocationId, Location> locationByLocationId = weatherService.getAllLocations()
                .stream()
                .collect(Collectors.toMap(
                        Location::getLocationId,
                        Function.identity()
                ));

        Map<Long, Crop> cropById = cropService.getCrops()
                .stream()
                .collect(Collectors.toMap(
                        Crop::getId,
                        Function.identity()
                ));

        return cropItems.stream()
                .map(item -> {
                    LocationId locationId = new LocationId();
                    locationId.setCity(item.getCity());
                    locationId.setCountry(item.getCountry());

                    Location location = locationByLocationId.get(locationId);
                    Crop crop = cropById.get(item.getCropId());

                    if (location == null) {
                        throw new IllegalArgumentException(
                                "No location for " + item.getCity() + ", " + item.getCountry()
                        );
                    }

                    if (crop == null) {
                        throw new IllegalArgumentException(
                                "No crop with id " + item.getCropId()
                        );
                    }

                    return new CropItemData(crop, location);
                })
                .toList();
    }

    public record GrowthResponse(Object message) {
    }
}
