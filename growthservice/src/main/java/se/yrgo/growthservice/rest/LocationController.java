package se.yrgo.growthservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.growthservice.service.WeatherService;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final WeatherService weatherService;

    public LocationController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public List<LocationDto> getAllLocations() {
        return weatherService.getAllLocations()
                .stream()
                .map(loc -> new LocationDto(loc.getCity(), loc.getCountry()))
                .toList();
    }

    public record LocationDto(String city, String country) {}
}
