package hiFes.hiFes.repository;

import hiFes.hiFes.entity.Board;
import hiFes.hiFes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository<Entity 클래스, PK 타입>
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContaining(String keyword);

    Board findByUser(User user);
}
