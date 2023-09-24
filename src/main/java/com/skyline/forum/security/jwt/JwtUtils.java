package com.skyline.forum.security.jwt;

import com.skyline.forum.security.UserDetailSecurity;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

@Slf4j
@Component
public class JwtUtils {
    @Value("${api.security.jwt.key}")
    private String TOKEN_KEY;
    @Value("${api.security.jwt.expiration.time}")
    private long EXPIRATION_JWT_MINUTES;

    public String generateJwtToken(Authentication authentication) {
        UserDetailSecurity userDetailSecurity = (UserDetailSecurity) authentication.getPrincipal();

        return Jwts.builder()
            .setId(String.valueOf(userDetailSecurity.getId()))
            .setSubject(userDetailSecurity.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + EXPIRATION_JWT_MINUTES))
            .signWith(HS512, TOKEN_KEY)
            .compact();
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + EXPIRATION_JWT_MINUTES))
            .signWith(HS512, TOKEN_KEY)
            .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public static String getUserNameOfToken() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
