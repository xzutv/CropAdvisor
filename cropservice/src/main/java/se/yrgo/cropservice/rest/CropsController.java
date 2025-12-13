package se.yrgo.cropservice.rest;

import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.cropservice.dao.CreateCropRequest;
import se.yrgo.cropservice.entities.Crop;
import se.yrgo.cropservice.entities.enums.PlantType;
import se.yrgo.cropservice.entities.enums.SoilType;
import se.yrgo.cropservice.entities.enums.SunExposure;
import se.yrgo.cropservice.service.CropService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CropsController {

 private final CropService cropService;

 public CropsController(CropService cropService) {
        this.cropService = cropService;
 }

 @GetMapping("/")
    public ResponseEntity<List<Crop>> getCrops() {
     List<Crop> crops = cropService.getAllCrops();
     return ResponseEntity.ok(crops);
 }

 @GetMapping("/search")
    public ResponseEntity<List<Crop>> searchByType(@RequestParam PlantType type) {
     List<Crop> crops = cropService.findByPlantType(type);
     return ResponseEntity.ok(crops);
 }

 @PostMapping
    public ResponseEntity<Crop> createCrop(@RequestBody Crop newCrop) {
     try {
         Crop crop = cropService.createCompleteCropWithProfile(
                 newCrop.getName(),
                 newCrop.getLatinName(),
                 newCrop.getType(),
                 newCrop.getEnvoirmentProfile(),
                 newCrop.getRequirements()
         );
         return ResponseEntity.status(HttpStatus.CREATED).body(crop);
     } catch (IllegalArgumentException e) {
         return ResponseEntity.badRequest().build();
     }
 }

 @PostMapping("/create-default")
    public ResponseEntity<Crop> createDefaultCrop(@RequestBody CreateCropRequest request) {

     Crop crop = cropService.createProfileWithDefaults(
             request.getName(),
             request.getLatinName(),
             request.getType(),
             request.getSoilType(),
             request.getSunExposure());
     return ResponseEntity.ok(crop);
 }

 @GetMapping("/{cropId}")
    public ResponseEntity<Crop> getCropById(@PathVariable Long cropId) {
     Crop crop = cropService.getCropById(cropId);
     return ResponseEntity.ok(crop);
 }
}
