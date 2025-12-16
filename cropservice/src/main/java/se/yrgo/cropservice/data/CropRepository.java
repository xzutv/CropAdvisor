package se.yrgo.cropservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.yrgo.cropservice.entities.Crop;
import se.yrgo.cropservice.entities.enums.PlantType;

import java.util.List;
import java.util.Optional;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    List<Crop> findByType(PlantType type);

    Crop findByName(String name);

    Crop findByLatinName(String latinName);

}
