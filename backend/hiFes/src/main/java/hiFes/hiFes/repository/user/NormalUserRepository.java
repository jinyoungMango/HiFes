package hiFes.hiFes.repository.user;

import hiFes.hiFes.domain.user.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NormalUserRepository extends JpaRepository<NormalUser, Long> {
    Optional<NormalUser> findByEmail(String email);
    Optional<NormalUser> findByRefreshToken(String refreshToken);

}
