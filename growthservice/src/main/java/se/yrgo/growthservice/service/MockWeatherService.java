package se.yrgo.growthservice.service;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import se.yrgo.growthservice.dao.LocalWeatherData;
import se.yrgo.growthservice.domain.weather.Location;
import se.yrgo.growthservice.domain.weather.Weather;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class MockWeatherService {

    private Location gbgLocation = new Location(1, "Gothenburg", "Sweden", null);


    private List<Weather> weathers = List.of(
            new Weather(
                    BigDecimal.valueOf(11.2),
                    60,
                    BigDecimal.valueOf(12.0),
                    BigDecimal.valueOf(5.0),
                    BigDecimal.valueOf(2),
                    LocalDate.now(),
                    gbgLocation
            )
    );


    public MockWeatherService() {
    }

    public List<Weather> getAllWeather() {
        return weathers;
    }

    public List<Location> getAllLocations() {
        return List.of(gbgLocation);
    }

    public List<Weather> getLocalWeather(LocalWeatherData localWeatherData) {
        return weathers.stream()
                .filter(weather -> weather
                        .getLocation()
                        .getCity()
                        .equalsIgnoreCase(localWeatherData.city())
                        && weather
                        .getLocation()
                        .getCountry()
                        .equalsIgnoreCase(localWeatherData.country()))
                .toList();
    }

    public Location getLocationById(Long id) {
        return getAllLocations().stream()
                .filter(location -> location.getId() != null && id.equals(location.getId().longValue()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No location with id " + id));
    }
}
