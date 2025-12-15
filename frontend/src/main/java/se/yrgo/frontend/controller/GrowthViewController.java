package se.yrgo.frontend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import se.yrgo.frontend.service.GrowthServiceClient;

@RestController
@RequestMapping
public class GrowthViewController {

    private final GrowthServiceClient growthServiceClient;


    public GrowthViewController(GrowthServiceClient growthServiceClient) {
        this.growthServiceClient = growthServiceClient;
    }
}
