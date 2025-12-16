package se.yrgo.weatherservice.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.yrgo.weatherservice.domain.Location;
import se.yrgo.weatherservice.domain.Weather;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    @Query("select w from Weather w where w.location = :location")
    public List<Weather> findByLocation(@Param("location") Location location);
}
