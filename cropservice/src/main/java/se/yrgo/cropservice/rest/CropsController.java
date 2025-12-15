package se.yrgo.cropservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.cropservice.dao.CreateCropRequest;
import se.yrgo.cropservice.dto.CropDTO;
import se.yrgo.cropservice.entities.Crop;
import se.yrgo.cropservice.entities.enums.PlantType;
import se.yrgo.cropservice.exceptions.CropNotFoundException;
import se.yrgo.cropservice.mapper.CropMapper;
import se.yrgo.cropservice.service.CropService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CropsController {

    private final CropService cropService;
    private final CropMapper mapper;

    public CropsController(CropService cropService, CropMapper mapper) {
        this.cropService = cropService;
        this.mapper = mapper;
    }

    @GetMapping()
    public ResponseEntity<List<CropDTO>> getCrops() {
        List<Crop> crops = cropService.getAllCrops();
        var dto = mapper.toDTOList(crops);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CropDTO>> searchByType(@RequestParam PlantType type) {
        List<Crop> crops = cropService.findByPlantType(type);
        var dto = mapper.toDTOList(crops);
        return ResponseEntity.ok(dto);
    }


    @PostMapping("/create-default")
    public ResponseEntity<CropDTO> createCropWithDefaultProfile(@RequestBody CreateCropRequest request) {

        try {
            Crop crop = cropService.createProfileWithDefaults(
                    request.getName(),
                    request.getLatinName(),
                    request.getType(),
                    request.getSoilType(),
                    request.getSunExposure());

            CropDTO dto = mapper.toDTO(crop);

            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/crop/{id}")
    public ResponseEntity<CropDTO> getCropById(@PathVariable Long id) {
        return cropService.getCropById(id)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CropNotFoundException("Crop not found with id: " + id));
    }

    @GetMapping("/crop/name/{name}")
    public ResponseEntity<CropDTO> getCropByName(@PathVariable String name) {
        return cropService.findCropByName(name)
                .map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CropNotFoundException("Crop not found with name: " + name));
    }


}
