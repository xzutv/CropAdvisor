package se.yrgo.growthservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.growthservice.dao.LocalWeatherData;
import se.yrgo.growthservice.data.StorageRepository;
import se.yrgo.growthservice.domain.advice.Advice;
import se.yrgo.growthservice.domain.crop.Crop;
import se.yrgo.growthservice.domain.crop.enums.PlantType;
import se.yrgo.growthservice.domain.weather.Location;
import se.yrgo.growthservice.domain.weather.Weather;
import se.yrgo.growthservice.entities.CropItem;
import se.yrgo.growthservice.service.CropService;
import se.yrgo.growthservice.service.WeatherService;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdviceService {

    private final WeatherService weatherService;
    private final CropService cropService;
    private final StorageRepository storageRepository;

    public AdviceService(WeatherService weatherService, CropService cropService, StorageRepository storageRepository) {
        this.weatherService = weatherService;
        this.cropService = cropService;
        this.storageRepository = storageRepository;
    }

    // Returns all advices for all crop items in storage
    public List<List<Advice>> getAllAdvices() {
        List<CropItem> items = storageRepository.findAll();
        List<List<Advice>> allAdvices = new ArrayList<>();
        for (CropItem item : items) {
            allAdvices.add(getAdvicesForItem(item));
        }
        return allAdvices;
    }

    // Returns a list of advices for a single crop item
    private List<Advice> getAdvicesForItem(CropItem item) {
        Crop crop = cropService.getCropById(item.getCropId());
        Location location = weatherService.getLocationById(item.getLocationId());

        List<Weather> weatherList = weatherService.getLocalWeather(
                new LocalWeatherData(location.getCity(), location.getCountry())
        );
        Weather weather = weatherList.isEmpty() ? null : weatherList.get(0);

        return evaluate(crop, weather, location);
    }

    // Evaluates multiple advice types for a crop under current weather
    private List<Advice> evaluate(Crop crop, Weather weather, Location location) {
        List<Advice> advices = new ArrayList<>();

        if (weather == null) {
            advices.add(new Advice("Weather data unavailable", false));
            return advices;
        }

        // Water advice
        if (weather.getRain() < crop.getRequirements().getWaterLitersPerWeek()) {
            advices.add(new Advice("Water your " + crop.getName(), true));
        } else {
            advices.add(new Advice(crop.getName() + " has enough water", false));
        }

        // Temperature advice
        if (crop.getRequirements().isFrostSensitive() && weather.getTemp() < crop.getRequirements().getOptimalTempMin()) {
            advices.add(new Advice("Protect " + crop.getName() + " from frost", true));
        }
        if (crop.getRequirements().isHeatSensitive() && weather.getTemp() > crop.getRequirements().getOptimalTempMax()) {
            advices.add(new Advice("Provide shade for " + crop.getName(), true));
        }

        // Sun exposure advice
        switch (crop.getEnviromentProfile().getSunExposure()) {
            case FULL_SUN -> {
                if (weather.getCloudiness() > 80) {
                    advices.add(new Advice(crop.getName() + " may need more sunlight", true));
                }
            }
            case PARTIAL_SHADE, PART_SUN -> {
                if (weather.getTemp() > crop.getRequirements().getOptimalTempMax()) {
                    advices.add(new Advice(crop.getName() + " may overheat, provide shade", true));
                }
            }
        }

        // Seasonal advice
        Month currentMonth = Month.of(weather.getTimestamp().getMonthValue());
        if (!isMonthInRange(currentMonth, crop.getEnviromentProfile().getPlantingSeason())) {
            advices.add(new Advice(crop.getName() + " is not in the optimal planting season", false));
        }
        if (isMonthInRange(currentMonth, crop.getEnviromentProfile().getHarvestSeason())) {
            advices.add(new Advice("It's harvest time for " + crop.getName(), true));
        }

        return advices;
    }

    private boolean isMonthInRange(Month current, String season) {
        if (season == null || season.isEmpty()) return false;
        String[] parts = season.split("-");
        Month start = Month.valueOf(parts[0].toUpperCase());
        Month end = Month.valueOf(parts[1].toUpperCase());
        return !current.isBefore(start) && !current.isAfter(end);
    }
}
