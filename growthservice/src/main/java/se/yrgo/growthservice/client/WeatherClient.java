package se.yrgo.growthservice.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import se.yrgo.growthservice.data.LocalWeatherData;
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
        return client.get().uri("/weather").retrieve().body(List.class);
    }

    public List<Location> getAllLocations() {
        return client.get().uri("/locations").retrieve().body(List.class);
    }

    public List<Weather> getLocalWeather(LocalWeatherData localWeatherData) {
        if (localWeatherData == null) {
            return List.of();
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.forEach((key, value) -> params.add(key, value.toString()));

        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/models")
                        .queryParams(params).build())
                .retrieve()
                .body(List.class);
    }
}
