package se.yrgo.growthservice.dto;

import se.yrgo.growthservice.domain.advice.Advice;
import se.yrgo.growthservice.domain.crop.Crop;
import se.yrgo.growthservice.domain.weather.Weather;

import java.util.List;

public class AdviceWithContextDTO {
    private Crop crop;
    private Weather currentWeather;
    private List<Advice> advices;

    public AdviceWithContextDTO() {}

    public AdviceWithContextDTO(Crop crop, Weather currentWeather, List<Advice> advices) {
        this.crop = crop;
        this.currentWeather = currentWeather;
        this.advices = advices;
    }

    public Crop getCrop() { return crop; }
    public void setCrop(Crop crop) { this.crop = crop; }

    public Weather getCurrentWeather() { return currentWeather; }
    public void setCurrentWeather(Weather currentWeather) { this.currentWeather = currentWeather; }

    public List<Advice> getAdvices() { return advices; }
    public void setAdvices(List<Advice> advices) { this.advices = advices; }
}