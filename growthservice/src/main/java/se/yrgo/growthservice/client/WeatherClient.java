package se.yrgo.growthservice.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import se.yrgo.growthservice.dao.LocalWeatherData;
import se.yrgo.growthservice.domain.weather.Location;
import se.yrgo.growthservice.domain.weather.Weather;

import java.util.List;

@Component
public class WeatherClient {

    private final RestClient client;

    public WeatherClient(RestClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8082").defaultHeader("content-type", MediaType.APPLICATION_JSON_VALUE).build();
    }

    public List<Weather> getAllWeather() {
        return client.get()
                .uri("/weather")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Weather>>() {});
    }

    public List<Location> getAllLocations() {
        return client.get()
                .uri("/locations")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Location>>() {});
    }

    public List<Weather> getLocalWeather(LocalWeatherData localWeatherData) {
        if (localWeatherData == null) {
            return List.of();
        }

        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather-location")
                        .queryParam("city", localWeatherData.city())
                        .queryParam("country", localWeatherData.country())
                        .build()
                )
                .retrieve()
                .body(new ParameterizedTypeReference<List<Weather>>() {});
    }
}
