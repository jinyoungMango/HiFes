package hiFes.hiFes.config.jwt;

import hiFes.hiFes.domain.NormalUser;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
import java.time.Duration;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class TokenProvider {
    private final JwtProperties jwtProperties;
    
    public String generateToken(NormalUser user, Duration expiredAt){
        Date now = new Date();
        return makeToken(new Date(now.getTime + expiredAt.toMillis()), user);
    }

    private String makeToken(Date expiry, NormalUser user){
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public boolean validToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Authentication getAuthentication(String token){
        Claims claims = getClass(token);
        set< SimpleGrantedAuthority> authorities = Collections.sigleton(new SimpleGrantedAuthority(
                )))

    }
}
