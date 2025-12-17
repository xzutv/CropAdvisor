package se.yrgo.weatherservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.weatherservice.data.LocationRepository;
import se.yrgo.weatherservice.data.WeatherRepository;
import se.yrgo.weatherservice.domain.Location;
import se.yrgo.weatherservice.domain.Weather;

import java.util.List;

@RestController
public class WeatherRestController {
    private final WeatherRepository weatherRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public WeatherRestController(WeatherRepository weatherRepository, LocationRepository locationRepository) {
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/weather")
    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }


    // get all weather by location, returns empty result if no match is found
    @GetMapping("/weather-location")
    public List<Weather> getLocalWeather(@RequestParam String city, @RequestParam String country) {
        Location location;

        if (locationRepository.findByCityAndCountry(city, country).isEmpty()) {
            return List.of();
        } else {
            location = locationRepository.findByCityAndCountry(city, country).getFirst();
        }

        return weatherRepository.findByLocation(location);
    }
}
