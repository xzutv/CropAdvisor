package se.yrgo.growthservice.dto;

import se.yrgo.growthservice.domain.advice.Advice;
import se.yrgo.growthservice.domain.crop.Crop;
import se.yrgo.growthservice.domain.weather.Weather;
import se.yrgo.growthservice.entities.CropItem;

import java.util.List;

public class AdviceWithContextDTO {
    private CropItem cropItem;
    private Crop crop;
    private Weather weather;
    private List<Advice> advices;

    public AdviceWithContextDTO() {}

    public AdviceWithContextDTO(CropItem cropItem, Crop crop, Weather weather, List<Advice> advices) {
        this.cropItem = cropItem;
        this.crop = crop;
        this.weather = weather;
        this.advices = advices;
    }

    public CropItem getCropItem() { return cropItem; }
    public void setCropItem(CropItem cropItem) { this.cropItem = cropItem; }

    public Crop getCrop() { return crop; }
    public void setCrop(Crop crop) { this.crop = crop; }

    public Weather getWeather() { return weather; }
    public void setWeather(Weather weather) { this.weather = weather; }

    public List<Advice> getAdvices() { return advices; }
    public void setAdvices(List<Advice> advices) { this.advices = advices; }
}