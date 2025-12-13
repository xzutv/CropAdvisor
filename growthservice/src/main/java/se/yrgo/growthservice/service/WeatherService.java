package se.yrgo.growthservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.growthservice.client.WeatherClient;
import se.yrgo.growthservice.dao.LocalWeatherData;
import se.yrgo.growthservice.domain.weather.Location;
import se.yrgo.growthservice.domain.weather.Weather;

import java.util.List;

@Service
public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public List<Weather> getAllWeather() {
        return weatherClient.getAllWeather();
    }

    public List<Location> getAllLocations() {
        return weatherClient.getAllLocations();
    }

    public List<Weather> getLocalWeather(LocalWeatherData localWeatherData) {
        return weatherClient.getLocalWeather(localWeatherData);
    }
}
