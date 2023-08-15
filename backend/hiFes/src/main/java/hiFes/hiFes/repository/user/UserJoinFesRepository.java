package hiFes.hiFes.repository.user;

import hiFes.hiFes.domain.festival.OrganizedFestival;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.domain.user.UserJoinFes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJoinFesRepository extends JpaRepository<UserJoinFes,Long> {

    @Query("SELECT pf FROM UserJoinFes pf WHERE pf.organizedFestival.id = :festivalId")
    List<UserJoinFes> findByOrganizedFestivalId(Long festivalId);

    boolean existsByNormalUserAndOrganizedFestival(NormalUser normalUser, OrganizedFestival festival);

    UserJoinFes findByNormalUserAndOrganizedFestival(NormalUser normalUser, OrganizedFestival festival);
}
