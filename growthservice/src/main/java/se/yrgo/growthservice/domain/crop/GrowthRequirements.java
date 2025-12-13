package se.yrgo.growthservice.domain.crop;

public class GrowthRequirements {

    private double optimalTempMin;
    private double optimalTempMax;

    private double waterLitersPerWeek;

    private boolean frostSensitive;
    private boolean heatSensitive;

    public GrowthRequirements() {}

    public GrowthRequirements(double optimalTempMin, double optimalTempMax, double waterLitersPerWeek, boolean frostSensitive, boolean heatSensitive) {
        this.optimalTempMin = optimalTempMin;
        this.optimalTempMax = optimalTempMax;
        this.waterLitersPerWeek = waterLitersPerWeek;
        this.frostSensitive = frostSensitive;
        this.heatSensitive = heatSensitive;
    }

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
