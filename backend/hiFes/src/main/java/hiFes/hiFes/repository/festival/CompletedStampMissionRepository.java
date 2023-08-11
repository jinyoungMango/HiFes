package hiFes.hiFes.repository.festival;

import hiFes.hiFes.domain.festival.CompletedStampMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompletedStampMissionRepository extends JpaRepository<CompletedStampMission, Long> {
    // join. festival 정보를 얻어와야 함
    @Query("SELECT sm.missionId " +
            "FROM CompletedStampMission csm, StampMission sm " +
            "WHERE csm.stampMission = sm AND csm.normalUser.id = :userId AND sm.organizedFestival.id = :festivalId")
    List<Long> findCompletedStampMission_idByNormalUser_idAndOrganizedFestival_FestivalId(@Param("userId") Long userId, @Param("festivalId") Long festivalId);

    @Query("SELECT COUNT(sm.missionId) " +
            "FROM CompletedStampMission csm, StampMission sm " +
            "WHERE csm.stampMission = sm AND csm.normalUser.id = :userId AND sm.organizedFestival.id = :festivalId")
    Long countCompletedStampMissionByNormalUser_idAndOrganizedFestival_FestivalId(@Param("userId") Long userId, @Param("festivalId") Long festivalId);

}
