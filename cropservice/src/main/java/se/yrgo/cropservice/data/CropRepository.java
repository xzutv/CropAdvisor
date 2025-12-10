package se.yrgo.cropservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.yrgo.cropservice.entities.Crop;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

}
