package hiFes.hiFes.service;

import hiFes.hiFes.config.jwt.TokenProvider;
import hiFes.hiFes.domain.NormalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();

        NormalUser user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofDays(2));
    }
}
