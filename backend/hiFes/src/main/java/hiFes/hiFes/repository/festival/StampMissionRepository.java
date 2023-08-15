package hiFes.hiFes.repository.festival;

import hiFes.hiFes.domain.festival.StampMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StampMissionRepository extends JpaRepository<StampMission, Long> {
    List<StampMission> findByOrganizedFestival_festivalId(long festivalId);

    // 특정 행사의 미션 개수
    @Query("SELECT COUNT(sm) FROM StampMission sm WHERE sm.organizedFestival.id = :festivalId")
    Long countStampMissionsByFestivalId(@Param("festivalId") Long festivalId);

}

