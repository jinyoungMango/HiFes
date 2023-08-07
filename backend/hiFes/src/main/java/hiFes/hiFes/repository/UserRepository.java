package hiFes.hiFes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hiFes.hiFes.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // 이미 email 을 통해 생성된 사용자인지 체크
}
