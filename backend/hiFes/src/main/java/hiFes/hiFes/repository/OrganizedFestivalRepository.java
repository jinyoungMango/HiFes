package hiFes.hiFes.repository;

import hiFes.hiFes.domain.Marker;
import hiFes.hiFes.domain.OrganizedFestival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizedFestivalRepository extends JpaRepository<OrganizedFestival, Long> {
    //List<OrganizedFestival> findByHost_hostId(Long hostId);

    List<OrganizedFestival> findByHostUser_Id(Long hostUserId);

    @Query(value = "SELECT * FROM OrganizedFestival order by RAND() limit 3",nativeQuery = true)
    List<OrganizedFestival> findAll();

}

