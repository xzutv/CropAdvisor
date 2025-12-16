package se.yrgo.growthservice.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.growthservice.domain.advice.Advice;
import se.yrgo.growthservice.entities.CropItem;
import se.yrgo.growthservice.service.AdviceService;
import se.yrgo.growthservice.service.StorageService;

import java.util.List;

@RestController
@RequestMapping("/api/advice")
public class AdviceController {

    private final AdviceService adviceService;
    private final StorageService storageService;

    public AdviceController(AdviceService adviceService, StorageService storageService) {
        this.adviceService = adviceService;
        this.storageService = storageService;
    }

    @GetMapping
    public List<List<Advice>> getAllAdvices() {
        return adviceService.getAllAdvices();
    }

    @GetMapping("/{itemId}")
    public List<Advice> getAdviceForItem(@PathVariable Long itemId) {
        CropItem item = storageService.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CropItem not found"));
        return adviceService.getAdvicesForItem(item);
    }
}
