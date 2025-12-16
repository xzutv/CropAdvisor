package se.yrgo.growthservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.growthservice.dao.LocalWeatherData;
import se.yrgo.growthservice.data.CropItemRepository;
import se.yrgo.growthservice.domain.advice.Advice;
import se.yrgo.growthservice.domain.crop.Crop;
import se.yrgo.growthservice.domain.crop.enums.SunExposure;
import se.yrgo.growthservice.domain.weather.Location;
import se.yrgo.growthservice.domain.weather.Weather;
import se.yrgo.growthservice.entities.CropItem;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdviceService {

    private final WeatherService weatherService;
    private final CropService cropService;
    private final CropItemRepository cropItemRepository;

    public AdviceService(WeatherService weatherService, CropService cropService, CropItemRepository cropItemRepository) {
        this.weatherService = weatherService;
        this.cropService = cropService;
        this.cropItemRepository = cropItemRepository;
    }

    public List<List<Advice>> getAllAdvices() {
        List<CropItem> items = cropItemRepository.findAll();
        List<List<Advice>> allAdvices = new ArrayList<>();
        for (CropItem item : items) {
            allAdvices.add(getAdvicesForItem(item));
        }
        return allAdvices;
    }


    public List<Advice> getAdvicesForItem(CropItem item) {
        Crop crop = cropService.getCropById(item.getCropId());

        Location location = weatherService.getLocationByCityAndCountry(item.getCity(), item.getCountry());
        if (location == null) {
            throw new IllegalArgumentException(
                    "CropItem " + item.getId() + " har ingen location i WeatherService för " +
                            item.getCity() + ", " + item.getCountry()
            );
        }

        LocalWeatherData weatherData = new LocalWeatherData(location.getCity(), location.getCountry());
        List<Weather> weatherList = weatherService.getLocalWeather(weatherData);
        Weather weather = weatherList.isEmpty() ? null : weatherList.get(0);

        return evaluate(crop, weather);
    }

    private List<Advice> evaluate(Crop crop, Weather weather) {
        List<Advice> advices = new ArrayList<>();
        if (weather == null) {
            advices.add(new Advice("Weather data unavailable", false));
            return advices;
        }

        BigDecimal temp = weather.getTemp();
        BigDecimal rain = weather.getRain();
        BigDecimal clouds = weather.getClouds();
        BigDecimal wind = weather.getWind();
        int humidity = weather.getHumidity();

        // Water advice
        if (rain.compareTo(BigDecimal.valueOf(crop.getRequirements().getWaterLitersPerWeek())) < 0) {
            advices.add(new Advice("Water your " + crop.getName(), true));
        } else {
            advices.add(new Advice(crop.getName() + " has enough water", false));
        }

        // Temperature advice
        double minTemp = crop.getRequirements().getOptimalTempMin();
        double maxTemp = crop.getRequirements().getOptimalTempMax();
        double warningThreshold = 2.0;

        if (crop.getRequirements().isFrostSensitive()) {
            if (temp.doubleValue() < minTemp) {
                advices.add(new Advice("Protect " + crop.getName() + " from frost", true));
            } else if (temp.doubleValue() < minTemp + warningThreshold) {
                advices.add(new Advice(crop.getName() + " is approaching cold stress", true));
            }
        }

        if (crop.getRequirements().isHeatSensitive()) {
            if (temp.doubleValue() > maxTemp) {
                advices.add(new Advice("Provide shade for " + crop.getName(), true));
            } else if (temp.doubleValue() > maxTemp - warningThreshold) {
                advices.add(new Advice(crop.getName() + " is approaching heat stress", true));
            }
        }

        // Sun exposure advice
        SunExposure sun = crop.getEnviromentProfile().getSunExposure();
        switch (sun) {
            case FULL_SUN -> {
                if (clouds.compareTo(BigDecimal.valueOf(80)) > 0) {
                    advices.add(new Advice(crop.getName() + " may need more sunlight", true));
                }
            }
            case PARTIAL_SHADE, PART_SUN -> {
                if (temp.compareTo(BigDecimal.valueOf(maxTemp)) > 0) {
                    advices.add(new Advice(crop.getName() + " may overheat, provide shade", true));
                }
            }
        }

        // Wind advice
        if (wind.compareTo(BigDecimal.valueOf(20)) > 0) {
            advices.add(new Advice(crop.getName() + " may be affected by strong wind", true));
        }

        // Humidity advice
        if (humidity < 30) {
            advices.add(new Advice(crop.getName() + " may need extra water due to low humidity", true));
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
        if (parts.length != 2) return false;

        try {
            int startMonth = Month.valueOf(parts[0].trim().toUpperCase()).getValue();
            int endMonth = Month.valueOf(parts[1].trim().toUpperCase()).getValue();
            int currentMonth = current.getValue();

            return startMonth <= endMonth
                    ? currentMonth >= startMonth && currentMonth <= endMonth
                    : currentMonth >= startMonth || currentMonth <= endMonth;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
