package se.yrgo.growthservice.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.growthservice.domain.advice.Advice;
import se.yrgo.growthservice.dto.AdviceWithContextDTO;
import se.yrgo.growthservice.entities.CropItem;
import se.yrgo.growthservice.domain.crop.Crop;
import se.yrgo.growthservice.domain.weather.Weather;
import se.yrgo.growthservice.dao.LocalWeatherData;
import se.yrgo.growthservice.service.AdviceService;
import se.yrgo.growthservice.service.CropService;
import se.yrgo.growthservice.service.StorageService;
import se.yrgo.growthservice.service.WeatherService;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/advice")
public class AdviceController {

    private final AdviceService adviceService;
    private final StorageService storageService;
    private final CropService cropService;
    private final WeatherService weatherService;

    public AdviceController(AdviceService adviceService,
                            StorageService storageService,
                            CropService cropService,
                            WeatherService weatherService) {
        this.adviceService = adviceService;
        this.storageService = storageService;
        this.cropService = cropService;
        this.weatherService = weatherService;
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

    @GetMapping("/{itemId}/context")
    public AdviceWithContextDTO getAdviceWithContext(@PathVariable Long itemId) {
        CropItem item = storageService.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CropItem not found"));

        Crop crop = cropService.getCropById(item.getCropId());
        LocalWeatherData weatherData = new LocalWeatherData(item.getCity(), item.getCountry());
        List<Weather> weatherList = weatherService.getLocalWeather(weatherData);
        Weather currentWeather = weatherList.isEmpty() ? null : weatherList.get(0);
        List<Advice> advices = adviceService.getAdvicesForItem(item);

        return new AdviceWithContextDTO(crop, currentWeather, advices);
    }

    @GetMapping("/all-with-context")
    public List<AdviceWithContextDTO> getAllAdvicesWithContext() {
        return storageService.findAll().stream()
                .flatMap(item -> {
                    Crop crop;
                    try {
                        crop = cropService.getCropById(item.getCropId());
                    } catch (IllegalArgumentException e) {
                        return Stream.empty();
                    }

                    LocalWeatherData weatherData = new LocalWeatherData(item.getCity(), item.getCountry());
                    List<Weather> weatherList = weatherService.getLocalWeather(weatherData);
                    Weather currentWeather = weatherList.isEmpty() ? null : weatherList.get(0);
                    List<Advice> advices = adviceService.getAdvicesForItem(item);

                    return Stream.of(new AdviceWithContextDTO(crop, currentWeather, advices));
                })
                .toList();
    }
}
