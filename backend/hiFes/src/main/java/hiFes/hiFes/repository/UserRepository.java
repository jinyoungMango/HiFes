package hiFes.hiFes.repository;

import hiFes.hiFes.domain.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<NormalUser, Long> {
    Optional<NormalUser> findByEmail(String email);
}
