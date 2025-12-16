package se.yrgo.weatherservice.dto;

import se.yrgo.weatherservice.domain.Weather;

import java.util.List;

public class WeatherList {
    List<Weather> weather;

    public WeatherList(List<Weather> weather) {
        this.weather = weather;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
