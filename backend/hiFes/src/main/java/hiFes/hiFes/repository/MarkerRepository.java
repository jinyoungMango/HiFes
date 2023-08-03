package hiFes.hiFes.repository;

import hiFes.hiFes.domain.Marker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkerRepository extends JpaRepository<Marker, Long> {
    List<Marker> findByOrganizedFestival_festivalId(long festivalId);
}
