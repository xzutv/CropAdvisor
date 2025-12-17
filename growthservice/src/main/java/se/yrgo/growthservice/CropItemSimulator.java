package se.yrgo.growthservice;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.yrgo.growthservice.data.CropItemRepository;
import se.yrgo.growthservice.domain.weather.Location;
import se.yrgo.growthservice.entities.CropItem;

@Component
public class CropItemSimulator {

    private final CropItemRepository cropItemRepository;

    @Autowired
    public CropItemSimulator(CropItemRepository cropItemRepository) {
        this.cropItemRepository = cropItemRepository;
    }

    @PostConstruct
    public void init() {

        CropItem cropItem1 = new CropItem(1L, "Gothenburg", "Sweden");
        CropItem cropItem2 = new CropItem(2L, "Gothenburg", "Sweden");

        cropItemRepository.save(cropItem1);
        cropItemRepository.save(cropItem2);
    }











}
