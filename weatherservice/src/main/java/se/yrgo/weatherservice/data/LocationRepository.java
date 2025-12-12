package se.yrgo.weatherservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.yrgo.weatherservice.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    public Location findByCity(String city);
}
