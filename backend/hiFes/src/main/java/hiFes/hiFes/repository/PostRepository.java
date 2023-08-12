package hiFes.hiFes.repository;

import hiFes.hiFes.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByIdDesc();
    List<Post> findAllByFestivalId(Long festivalId);
    @Query(
            value = "SELECT p FROM Post p WHERE p.organizedFestival.festivalId=:festivalId" +
                    " and p.postType=:postType"
    )
    List<Post> findAllByFestivalIdAndPostType(@Param("festivalId") Long festivalId, @Param("postType") String postType);
    List<Post> findByPostType(String postType);
}
