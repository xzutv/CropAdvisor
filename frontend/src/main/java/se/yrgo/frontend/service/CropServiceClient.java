package se.yrgo.frontend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.yrgo.frontend.model.Crop;

import java.util.List;

@Service
public class CropServiceClient {

    private final RestTemplate restTemplate;
    private final String cropServiceUrl;

    public CropServiceClient(RestTemplate restTemplate,
                             @Value("${cropservice.url}") String cropServiceUrl) {
        this.restTemplate = restTemplate;
        this.cropServiceUrl = cropServiceUrl;
    }

    public List<Crop> getAllCrops() {
        String url = cropServiceUrl + "/api";
        ResponseEntity<List<Crop>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Crop>>() {}
        );
        return response.getBody();
    }

    public Crop getCropById(Long id) {
        String url = cropServiceUrl + "/api/crop/" + id;
        return restTemplate.getForObject(url, Crop.class);
    }
}