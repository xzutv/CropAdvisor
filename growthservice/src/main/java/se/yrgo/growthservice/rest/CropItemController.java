package se.yrgo.growthservice.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.growthservice.entities.CropItem;
import se.yrgo.growthservice.service.CropItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cropitem")
public class CropItemController {

    private final CropItemService cropItemService;

    public CropItemController(CropItemService cropItemService) {
        this.cropItemService = cropItemService;
    }

    @GetMapping
    public List<CropItem> getAllCropItems() {
        return cropItemService.getAllCropItems();
    }

    @GetMapping("/{id}")
    public CropItem getCropItem(@PathVariable Long id) {

        return cropItemService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CropItem not found"));
    }

    @PostMapping
    public CropItem createCropItem(@RequestBody CropItemRequest request) {
        try {
            return cropItemService.createCropItem(
                    request.getCropId(),
                    request.getCity(),
                    request.getCountry()
            );
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public CropItem updateCropItem(@PathVariable Long id, @RequestBody CropItemRequest request) {
        try {
            return cropItemService.updateCropItem(id, request.getCropId(), request.getCity(), request.getCountry());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCropItem(@PathVariable Long id) {
        cropItemService.deleteCropItem(id);
    }

    @GetMapping("/by-crop/{cropId}")
    public Optional<CropItem> getCropItemsByCrop(@PathVariable Long cropId) {
        return cropItemService.getByCropId(cropId);
    }

    public static class CropItemRequest {
        private Long cropId;
        private String city;
        private String country;

        public Long getCropId() { return cropId; }
        public String getCity() { return city; }
        public String getCountry() { return country; }

        public void setCropId(Long cropId) { this.cropId = cropId; }
        public void setCity(String city) { this.city = city; }
        public void setCountry(String country) { this.country = country; }
    }
}
