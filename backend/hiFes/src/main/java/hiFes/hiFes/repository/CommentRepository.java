package hiFes.hiFes.repository;

import hiFes.hiFes.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

<<<<<<< HEAD
    List<Comment> findByIdOrderByIdDesc();
=======
    List<Comment> findAllByPostIdAndParentIsNull(Long postId);
>>>>>>> 90bac5b3010f0f969136fd409e9f527151aaf6e5
}
