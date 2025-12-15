package se.yrgo.growthservice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.yrgo.growthservice.data.StorageRepository;
import se.yrgo.growthservice.entities.CropItem;

@Component
public class CropItemSimulator {

    private final StorageRepository storageRepository;

    @Autowired
    public CropItemSimulator(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @PostConstruct
    public void init() {
        CropItem cropItem1 = new CropItem(1L, 1L);
        CropItem cropItem2 = new CropItem(2L, 2L);

        storageRepository.save(cropItem1);
        storageRepository.save(cropItem2);
    }











}
