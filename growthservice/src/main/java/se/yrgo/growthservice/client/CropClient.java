package se.yrgo.growthservice.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import se.yrgo.growthservice.dao.CreateCropRequest;
import se.yrgo.growthservice.domain.crop.Crop;
import se.yrgo.growthservice.domain.crop.enums.PlantType;

import java.util.List;

@Component
public class CropClient {
    private final RestClient client;

    public CropClient(RestClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8081/").defaultHeader("content-type", MediaType.APPLICATION_JSON_VALUE).build();
    }

    public List<Crop> getCrops() {
        return client.get().uri("/").retrieve().body(List.class);
    }

    public List<Crop> searchByType(PlantType plantType) {
        return client.get().uri("/serach").retrieve().body(List.class);
    }

    public Crop createCrop(Crop newCrop) {
        return client.post().uri("/").body(newCrop).retrieve().body(Crop.class);
    }

    public Crop createDefaultCrop(CreateCropRequest request) {
        return client.post().uri("/create-default").body(request).retrieve().body(Crop.class);
    }

    public Crop getCropById(Long cropId) {
        return client.get().uri("/" + cropId).retrieve().body(Crop.class);
    }

    public Crop findCropByName(String cropName) {
        return client.get().uri("/" + cropName).retrieve().body(Crop.class);
    }
}
