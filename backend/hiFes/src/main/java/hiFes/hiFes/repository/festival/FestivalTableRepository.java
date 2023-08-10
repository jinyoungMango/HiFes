package hiFes.hiFes.repository.festival;

import hiFes.hiFes.domain.festival.FestivalTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FestivalTableRepository extends JpaRepository<FestivalTable, Long> {
    List<FestivalTable> findByOrganizedFestival_festivalId(long festivalId);
    void deleteByOrganizedFestival_festivalId(long festivalId);
}

