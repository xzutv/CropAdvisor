package se.yrgo.growthservice.service;

import org.springframework.stereotype.Service;
import se.yrgo.growthservice.data.StorageRepository;
import se.yrgo.growthservice.entities.CropItem;

import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public List<CropItem> findAll() {
        return storageRepository.findAll();
    }

    public Optional<CropItem> findById(Long id) {
        return storageRepository.findById(id);
    }
}
