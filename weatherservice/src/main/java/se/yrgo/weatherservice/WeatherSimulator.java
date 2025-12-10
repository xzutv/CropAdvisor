package se.yrgo.weatherservice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.yrgo.weatherservice.data.LocationRepository;
import se.yrgo.weatherservice.data.WeatherRepository;
import se.yrgo.weatherservice.domain.Location;
import se.yrgo.weatherservice.domain.Weather;

import java.time.LocalDate;
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
        for (int i = 0; i < 24; i++) {
            Random random = new Random();
            Weather weather = new Weather();
            weather.setTimestamp(LocalDate.parse("2025-12-09"));
            weather.setTemp(random.nextDouble(-5, 13));
            weather.setRain(random.nextBoolean());
            weather.setHumidity(random.nextInt(20, 90));
            weather.setWind(random.nextDouble(5, 25) + "m/s");
            weather.setLocation(new Location("Gothenburg", "Sweden"));
            System.out.println(weather);
            weatherRepository.save(weather);
        }
    }
}
