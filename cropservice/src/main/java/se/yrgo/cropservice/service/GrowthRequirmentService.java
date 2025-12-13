package se.yrgo.cropservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.cropservice.entities.GrowthRequirements;

@Service
public class GrowthRequirmentService {

    public GrowthRequirements createDefaultRequirements(boolean isTropical) {

        GrowthRequirements requirements = new GrowthRequirements();

        if (isTropical) {
            requirements.setOptimalTempMin(20);
            requirements.setOptimalTempMax(35);
            requirements.setWaterLitersPerWeek(15.0);
            requirements.setFrostSensitive(true);
            requirements.setHeatSensitive(false);
        } else {
            requirements.setOptimalTempMin(10);
            requirements.setOptimalTempMax(25);
            requirements.setWaterLitersPerWeek(10.0);
            requirements.setFrostSensitive(false);
            requirements.setHeatSensitive(false);
        }
        return requirements;
    }

    /**
     * Checks if frost is a risk given the forecasted temperature
     */
    public boolean isFrostRisk(GrowthRequirements requirements, double forecastTempMin) {
        return requirements.isFrostSensitive() && forecastTempMin < 0;
    }

    /**
     * A simpler version of the method calculateWaterNeedsForTemperature.
     * We can use this to check if the plant needs more watering.
     */
    public boolean isHeatRisk(GrowthRequirements requirements, double forecastTempMax) {
        return requirements.isHeatSensitive() && forecastTempMax > 25;
    }

    public void updateRequirments(GrowthRequirements existing, GrowthRequirements update) {

        existing.setOptimalTempMin(update.getOptimalTempMin());
        existing.setOptimalTempMax(update.getOptimalTempMax());
        existing.setWaterLitersPerWeek(update.getWaterLitersPerWeek());
        existing.setFrostSensitive(update.isFrostSensitive());
        existing.setHeatSensitive(update.isHeatSensitive());


    }

}
