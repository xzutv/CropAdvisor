package se.yrgo.frontend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.yrgo.frontend.model.AdviceContext;
import se.yrgo.frontend.model.CropItem;
import se.yrgo.frontend.model.Location;

import java.util.List;

@Service
public class GrowthServiceClient {

    private final RestTemplate restTemplate;
    private final String growthServiceUrl;

    public GrowthServiceClient(RestTemplate restTemplate,
                               @Value("${growthservice.url}") String growthServiceUrl) {
        this.restTemplate = restTemplate;
        this.growthServiceUrl = growthServiceUrl;
    }

    public List<AdviceContext> getAllAdvicesWithContext() {
        String url = growthServiceUrl + "/api/advice/all-with-context";
        ResponseEntity<List<AdviceContext>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AdviceContext>>() {}
        );
        return response.getBody();
    }

    public List<Location> getAllLocations() {
        String url = growthServiceUrl + "/api/location";
        ResponseEntity<List<Location>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Location>>() {}
        );
        return response.getBody();
    }

    public CropItem createCropItem(CropItem cropItem) {
        String url = growthServiceUrl + "/api/cropitem";
        return restTemplate.postForObject(url, cropItem, CropItem.class);
    }

    public void deleteCropItem(Long id) {
        String url = growthServiceUrl + "/api/cropitem/" + id;
        restTemplate.delete(url);
    }

    public AdviceContext getAdviceContextForItem(Long itemId) {
        String url = growthServiceUrl + "/api/advice/" + itemId + "/context";
        return restTemplate.getForObject(url, AdviceContext.class);
    }
}
