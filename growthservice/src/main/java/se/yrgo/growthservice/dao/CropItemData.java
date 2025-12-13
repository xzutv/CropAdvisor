package se.yrgo.growthservice.dao;

import se.yrgo.growthservice.domain.crop.Crop;
import se.yrgo.growthservice.domain.weather.Location;

public record CropItemData(Crop crop, Location location) {}
