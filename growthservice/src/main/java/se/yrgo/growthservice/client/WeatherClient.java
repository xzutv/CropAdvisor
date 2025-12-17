package se.yrgo.growthservice.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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


    public List<Location> getAllLocations() {
        Location[] locations = client.get()
                .uri("/locations")
                .retrieve()
                .body(Location[].class);
        return List.of(locations);
    }

    public Location getLocationByCityAndCountry(String city, String country) {
        Location[] locations = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/locations/search")
                        .queryParam("city", city)
                        .queryParam("country", country)
                        .build())
                .retrieve()
                .body(Location[].class);

        if (locations.length > 0) {
            return locations[0];
        } else {
            throw new RuntimeException("Location not found for city: " + city + ", country: " + country);
        }
    }

    public List<Weather> getAllWeather() {
        Weather[] weathers = client.get()
                .uri("/weather")
                .retrieve()
                .body(Weather[].class);
        return List.of(weathers);
    }

    public List<Weather> getLocalWeather(LocalWeatherData localWeatherData) {
        if (localWeatherData == null) return List.of();

        Weather[] weathers = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather-location")
                        .queryParam("city", localWeatherData.city())
                        .queryParam("country", localWeatherData.country())
                        .build())
                .retrieve()
                .body(Weather[].class);

        return List.of(weathers);
    }
}
