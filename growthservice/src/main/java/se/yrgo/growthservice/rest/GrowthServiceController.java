package se.yrgo.growthservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.growthservice.domain.weather.Weather;
import se.yrgo.growthservice.service.WeatherService;

import java.util.List;

@RestController
@RequestMapping("/growth")
public class GrowthServiceController {


    private final WeatherService weatherService;

    public GrowthServiceController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // ?test=test   /advice/test
    @GetMapping("/advice")
    public GrowthResponse getGrowthAdvice(@RequestParam String crop, @RequestParam String date) {
        List<Weather> test = weatherService.getAllWeather();

        return new GrowthResponse("test");

    }



    private record GrowthResponse (String message) {}
}
