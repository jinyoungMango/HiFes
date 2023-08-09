package hiFes.hiFes.repository;

import hiFes.hiFes.domain.FestivalTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FestivalTableRepository extends JpaRepository<FestivalTable, Long> {
    List<FestivalTable> findByOrganizedFestival_festivalId(long festivalId);
}

