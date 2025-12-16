package se.yrgo.growthservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.growthservice.dao.LocalWeatherData;
import se.yrgo.growthservice.domain.weather.Location;
import se.yrgo.growthservice.domain.weather.Weather;

import java.time.LocalDate;
import java.util.List;

@Service
public class MockWeatherService {

    private Location gbgLocation = new Location(1, "Gothenburg", "Sweden", null);

    private List<Weather> weathers = List.of(
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(10.1, 0, "fast", false, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation),
            new Weather(30.1, 2, "vindyo", true, LocalDate.now(), gbgLocation)
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
                .filter(location -> location.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No location withd id" + id));
    }
}
