package hiFes.hiFes.repository.user;

import hiFes.hiFes.domain.user.HostUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HostUserRepository extends JpaRepository<HostUser, Long> {
    Optional<HostUser> findByEmail(String email);

    Optional<HostUser> findByRefreshToken(String refreshToken);

}
