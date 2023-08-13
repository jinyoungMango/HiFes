package hiFes.hiFes.repository;

import hiFes.hiFes.domain.Post;
import hiFes.hiFes.dto.postDto.PostOrganizedFestivalDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByIdDesc();
    List<Post> findAllByOrganizedFestival_FestivalId(Long festivalId);
//    @Query(value = "SELECT p FROM Post p WHERE p.organizedFestival.festivalId = :festivalId AND p.postType = :postType")
    List<Post> findAllByOrganizedFestival_FestivalIdAndPostType(@Param("festivalId") Long festivalId, @Param("postType") String postType);
    List<Post> findByPostType(String postType);
}
