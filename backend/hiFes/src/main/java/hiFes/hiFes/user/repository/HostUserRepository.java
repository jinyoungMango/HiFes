package hiFes.hiFes.user.repository;

import hiFes.hiFes.user.HostUser;
import hiFes.hiFes.user.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HostUserRepository extends JpaRepository<HostUser, Long> {
    Optional<HostUser> findByEmail(String email);
    Optional<HostUser> findByRefreshToken(String refreshToken);

    Optional<HostUser> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
