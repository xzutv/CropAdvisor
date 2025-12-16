package se.yrgo.weatherservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.yrgo.weatherservice.domain.Location;
import se.yrgo.weatherservice.domain.LocationId;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, LocationId> {
    @Query("select l from Location l where l.id.city = :city and l.id.country = :country")
    public List<Location> findByCityAndCountry(@Param("city") String city, @Param("country") String country);
}
