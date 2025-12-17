package se.yrgo.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.yrgo.frontend.model.AdviceContext;
import se.yrgo.frontend.model.Crop;
import se.yrgo.frontend.model.CropItem;
import se.yrgo.frontend.model.Location;
import se.yrgo.frontend.service.CropServiceClient;
import se.yrgo.frontend.service.GrowthServiceClient;

import java.util.List;

@Controller
@RequestMapping
public class GrowthViewController {

    private final GrowthServiceClient growthServiceClient;
    private final CropServiceClient cropServiceClient;

    public GrowthViewController(GrowthServiceClient growthServiceClient,
                                CropServiceClient cropServiceClient) {
        this.growthServiceClient = growthServiceClient;
        this.cropServiceClient = cropServiceClient;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/garden";
    }

    @GetMapping("/garden")
    public String showGarden(Model model) {
        List<AdviceContext> gardenItems = growthServiceClient.getAllAdvicesWithContext();
        model.addAttribute("gardenItems", gardenItems);
        return "garden";
    }

    @GetMapping("/garden/add")
    public String showAddCropForm(Model model) {
        List<Crop> crops = cropServiceClient.getAllCrops();
        List<Location> locations = growthServiceClient.getAllLocations();
        model.addAttribute("crops", crops);
        model.addAttribute("locations", locations);
        return "add-crop";
    }

    @PostMapping("/garden/add")
    public String addCropToGarden(@RequestParam Long cropId,
                                  @RequestParam String city,
                                  @RequestParam String country) {
        CropItem cropItem = new CropItem(cropId, city, country);
        growthServiceClient.createCropItem(cropItem);
        return "redirect:/garden";
    }

    @GetMapping("/garden/detail/{id}")
    public String showCropDetail(@PathVariable Long id, Model model) {
        AdviceContext adviceContext = growthServiceClient.getAdviceContextForItem(id);
        model.addAttribute("adviceContext", adviceContext);
        return "crop-detail";
    }

    @PostMapping("/garden/delete/{id}")
    public String deleteCropFromGarden(@PathVariable Long id) {
        growthServiceClient.deleteCropItem(id);
        return "redirect:/garden";
    }
}
