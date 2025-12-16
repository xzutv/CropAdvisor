package se.yrgo.weatherservice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.yrgo.weatherservice.data.LocationRepository;
import se.yrgo.weatherservice.data.WeatherRepository;
import se.yrgo.weatherservice.domain.Location;
import se.yrgo.weatherservice.domain.LocationId;
import se.yrgo.weatherservice.domain.Weather;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class WeatherSimulator {
    private final WeatherRepository weatherRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public WeatherSimulator(WeatherRepository weatherRepository, LocationRepository locationRepository) {
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void init() {
        List<Location> locations = locationRepository.findByCityAndCountry("Gothenburg", "Sweden");
        Location location;

        if (!locations.isEmpty()) {
            location = locations.getFirst();
        } else {
            location = new Location();
            location.setCity("Gothenburg");
            location.setCountry("Sweden");
            location = locationRepository.save(location);
        }

        for (int i = 0; i < 24; i++) {
            Random random = new Random();
            Weather weather = new Weather();
            weather.setTimestamp(LocalDate.parse("2025-12-09").plusDays(i));
            weather.setTemp(random.nextDouble(5, 13));
            weather.setRain(random.nextDouble(0, 5));
            weather.setHumidity(random.nextInt(20, 90));
            weather.setWind(random.nextDouble(5, 25));
            weather.setClouds(random.nextDouble(0, 100));
            weather.setLocation(location);
            System.out.println(weather);
            weatherRepository.save(weather);
        }
    }
}
