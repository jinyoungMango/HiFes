package hiFes.hiFes.repository;

import hiFes.hiFes.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByIdDesc();
    List<Post> findAllByOrganizedFestival_FestivalId(Long festivalId);
    List<Post> findAllByOrganizedFestival_FestivalIdAndPostType(@Param("festivalId") Long festivalId, @Param("postType") String postType);
    List<Post> findByPostType(String postType);
}
