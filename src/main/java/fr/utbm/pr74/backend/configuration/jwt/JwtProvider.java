package fr.utbm.pr74.backend.configuration.jwt;

import fr.utbm.pr74.backend.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
    private final int jwtExpirationInMs;
    private final SecretKey secretKey;

    @Autowired
    public JwtProvider(@Value("${app.jwtExpirationInMs}") int jwtExpirationInMs, @Value("${app.tokenSecretKey}") String tokenSecretKey) {
        this.jwtExpirationInMs = jwtExpirationInMs;
        this.secretKey = Keys.hmacShaKeyFor(tokenSecretKey.getBytes());
    }

    public String generateToken(Authentication authentication) {
        var userPrincipal = (User) authentication.getPrincipal();
        var now = new Date();
        var expiryDate = new Date(now.getTime() + jwtExpirationInMs);


        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public String getUserUsernameFromJWT(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | ExpiredJwtException e) {
            LOGGER.error("validateToken error {}", e.getMessage());
        }
        return false;
    }
}

