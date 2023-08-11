package hiFes.hiFes.repository.festival;

import hiFes.hiFes.domain.festival.OrganizedFestival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrganizedFestivalRepository extends JpaRepository<OrganizedFestival, Long> {
    //List<OrganizedFestival> findByHost_hostId(Long hostId);

    List<OrganizedFestival> findByHostUser_Id(Long hostUserId);
    @Query(value = "SELECT * FROM OrganizedFestival order by RAND() limit 3",nativeQuery = true)
    List<OrganizedFestival> findAll();

    // 주변 반경 10km 내 계산
    @Query("SELECT f FROM OrganizedFestival f WHERE " +
            "(6371 * ACOS(" +
            "COS( :latitude * 3.14159 / 180) * COS(f.fesLatitude * 3.14159 / 180) * COS((f.fesLongitude * 3.14159 / 180) - (:longitude * 3.14159 / 180)) + " +
            "SIN( :latitude * 3.14159 / 180) * SIN(f.fesLatitude * 3.14159 / 180) )) <= 10")
    List<OrganizedFestival> findOrganizedFestivalsByLocationWithin10Km(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude
    );

    List<OrganizedFestival> findByFesTitleContaining(String word);


}

