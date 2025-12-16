package se.yrgo.growthservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.growthservice.entities.CropItem;

import java.util.Optional;

public interface CropItemRepository extends JpaRepository<CropItem, Long> {
    Optional<CropItem> findByCropIdAndCityAndCountry(Long cropId, String city, String country);
}
