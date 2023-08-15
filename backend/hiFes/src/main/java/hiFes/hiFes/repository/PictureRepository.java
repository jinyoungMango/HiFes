package hiFes.hiFes.repository;

import hiFes.hiFes.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findALlByPostId(Long postId);
}
