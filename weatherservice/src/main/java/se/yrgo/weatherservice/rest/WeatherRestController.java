package se.yrgo.weatherservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.weatherservice.data.LocationRepository;
import se.yrgo.weatherservice.data.WeatherRepository;
import se.yrgo.weatherservice.domain.Location;
import se.yrgo.weatherservice.domain.Weather;
import se.yrgo.weatherservice.dto.LocationList;
import se.yrgo.weatherservice.dto.WeatherList;

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
    public ResponseEntity<WeatherList> getAllWeather() {
        return ResponseEntity.ok(new WeatherList(weatherRepository.findAll()));
    }

    @GetMapping("/locations")
    public ResponseEntity<LocationList> getAllLocations() {
        return ResponseEntity.ok(new LocationList(locationRepository.findAll()));
    }

    @GetMapping("/weather-location")
    public ResponseEntity<WeatherList> getLocalWeather(@RequestParam String city, @RequestParam String country) {
        Location location;

        if (locationRepository.findByCityAndCountry(city, country).isEmpty()) {
            return ResponseEntity.ok(new WeatherList(List.of()));
        } else {
            location = locationRepository.findByCityAndCountry(city, country).getFirst();
        }

        return ResponseEntity.ok(new WeatherList(weatherRepository.findByLocation(location)));
    }
}
