package se.yrgo.growthservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.growthservice.data.CropItemRepository;
import se.yrgo.growthservice.entities.CropItem;

import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    private final CropItemRepository cropItemRepository;

    public StorageService(CropItemRepository cropItemRepository) {
        this.cropItemRepository = cropItemRepository;
    }

    public List<CropItem> findAll() {
        return cropItemRepository.findAll();
    }

    public Optional<CropItem> findById(Long id) {
        return cropItemRepository.findById(id);
    }
}
