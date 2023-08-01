package hiFes.hiFes.user.repository;

import hiFes.hiFes.user.NormalUser;
import hiFes.hiFes.user.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NormalUserRepository extends JpaRepository<NormalUser, Long> {
    Optional<NormalUser> findByEmail(String email);
    Optional<NormalUser> findByRefreshToken(String refreshToken);

    Optional<NormalUser> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
}
