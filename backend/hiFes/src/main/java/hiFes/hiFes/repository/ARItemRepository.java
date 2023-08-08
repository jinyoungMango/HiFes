package hiFes.hiFes.repository;

import hiFes.hiFes.domain.ARItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ARItemRepository extends JpaRepository<ARItem, Long> {
    List<ARItem> findByOrganizedFestival_festivalId(long festivalId);
}

