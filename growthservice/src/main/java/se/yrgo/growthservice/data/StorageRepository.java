package se.yrgo.growthservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.yrgo.growthservice.entities.CropItem;

@Repository
public interface StorageRepository extends JpaRepository<CropItem, Long> {
}
