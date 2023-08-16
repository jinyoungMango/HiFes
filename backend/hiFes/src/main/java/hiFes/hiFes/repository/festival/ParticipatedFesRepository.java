package hiFes.hiFes.repository.festival;

import hiFes.hiFes.domain.festival.ParticipatedFes;
import hiFes.hiFes.domain.user.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipatedFesRepository extends JpaRepository<ParticipatedFes,Long> {
    List<ParticipatedFes> findByNormalUser_id(Long normalUserId);


    boolean existsByNormalUser_IdAndOrganizedFestival_FestivalId(Long normalUserId, Long festivalId);

    ParticipatedFes findByNormalUser_IdAndOrganizedFestival_FestivalId(Long normalId, Long festivalId);

//    @Query("SELECT pf FROM ParticipatedFes pf WHERE pf.organizedFestival.id = :festivalId")
//    List<ParticipatedFes> findByOrganizedFestivalId(Long festivalId);
}
