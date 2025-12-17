package se.yrgo.frontend.model;

import java.util.List;

public class AdviceContext {
    private CropItem cropItem;
    private Crop crop;
    private Weather weather;
    private List<Advice> advices;

    public AdviceContext() {
    }

    public CropItem getCropItem() {
        return cropItem;
    }

    public void setCropItem(CropItem cropItem) {
        this.cropItem = cropItem;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public List<Advice> getAdvices() {
        return advices;
    }

    public void setAdvices(List<Advice> advices) {
        this.advices = advices;
    }

    public long getActionableAdviceCount() {
        return advices.stream().filter(Advice::isActionRequired).count();
    }

    public boolean hasActionableAdvice() {
        return advices.stream().anyMatch(Advice::isActionRequired);
    }
}