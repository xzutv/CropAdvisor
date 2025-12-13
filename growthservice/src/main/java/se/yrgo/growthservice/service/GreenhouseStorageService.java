package se.yrgo.growthservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.growthservice.data.GreenhouseStorageRepository;
import se.yrgo.growthservice.entities.CropItem;

import java.util.List;
import java.util.Optional;

@Service
public class GreenhouseStorageService {

    private final GreenhouseStorageRepository storageRepository;

    public GreenhouseStorageService(GreenhouseStorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public List<CropItem> findAll() {
        return storageRepository.findAll();
    }

    public Optional<CropItem> findById(Long id) {
        return storageRepository.findById(id);
    }
}
