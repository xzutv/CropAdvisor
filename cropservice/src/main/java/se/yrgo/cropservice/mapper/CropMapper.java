package se.yrgo.cropservice.mapper;

import org.springframework.stereotype.Component;
import se.yrgo.cropservice.dto.CropDTO;
import se.yrgo.cropservice.dto.EnvironmentProfileDTO;
import se.yrgo.cropservice.dto.GrowthRequirementsDTO;
import se.yrgo.cropservice.entities.Crop;
import se.yrgo.cropservice.entities.EnvironmentProfile;
import se.yrgo.cropservice.entities.GrowthRequirements;

import java.util.ArrayList;
import java.util.List;

@Component
public class CropMapper {

    public CropDTO toDTO(Crop crop) {
        CropDTO dto = new CropDTO();
        dto.setId(crop.getId());
        dto.setName(crop.getName());
        dto.setLatinName(crop.getLatinName());
        dto.setType(crop.getType());
        dto.setEnvironmentProfile(toDTO(crop.getEnvironmentProfile()));
        dto.setRequirements(toDTO(crop.getRequirements()));
        return dto;
    }

    public EnvironmentProfileDTO toDTO(EnvironmentProfile profile) {
        EnvironmentProfileDTO dto = new EnvironmentProfileDTO();
        dto.setId(profile.getId());
        dto.setHarvestSeason(profile.getHarvestSeason());
        dto.setPlantingSeason(profile.getPlantingSeason());
        dto.setSoilType(profile.getSoilType());
        dto.setSoilPhMin(profile.getSoilPhMin());
        dto.setSoilPhMax(profile.getSoilPhMax());
        dto.setSunExposure(profile.getSunExposure());
        return dto;
    }

    public GrowthRequirementsDTO toDTO(GrowthRequirements requirements) {
        GrowthRequirementsDTO dto = new GrowthRequirementsDTO();
        dto.setFrostSensitive(requirements.isFrostSensitive());
        dto.setHeatSensitive(requirements.isHeatSensitive());
        dto.setOptimalTempMin(requirements.getOptimalTempMin());
        dto.setOptimalTempMax(requirements.getOptimalTempMax());
        dto.setWaterLitersPerWeek(requirements.getWaterLitersPerWeek());
        return dto;
    }

    public List<CropDTO> toDTOList(List<Crop> crops) {
        List<CropDTO> dtos = new ArrayList<>();
        for (var crop : crops) {
            dtos.add(toDTO(crop));
        }
        return dtos;
    }

}
