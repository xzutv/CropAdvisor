package se.yrgo.cropservice.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class GrowthRequirements {

    private double optimalTempMin;
    private double optimalTempMax;

    private double waterLitersPerWeek;

    private boolean frostSensitive;
    private boolean heatSensitive;

    public GrowthRequirements() {}

    public double getOptimalTempMin() {
        return optimalTempMin;
    }

    public void setOptimalTempMin(double optimalTempMin) {
        this.optimalTempMin = optimalTempMin;
    }

    public double getOptimalTempMax() {
        return optimalTempMax;
    }

    public void setOptimalTempMax(double optimalTempMax) {
        this.optimalTempMax = optimalTempMax;
    }

    public double getWaterLitersPerWeek() {
        return waterLitersPerWeek;
    }

    public void setWaterLitersPerWeek(double waterLitersPerWeek) {
        this.waterLitersPerWeek = waterLitersPerWeek;
    }

    public boolean isFrostSensitive() {
        return frostSensitive;
    }

    public void setFrostSensitive(boolean frostSensitive) {
        this.frostSensitive = frostSensitive;
    }

    public boolean isHeatSensitive() {
        return heatSensitive;
    }

    public void setHeatSensitive(boolean heatSensitive) {
        this.heatSensitive = heatSensitive;
    }
}
