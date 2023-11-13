package com.ead.notification.configs.security;

import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class JwtProvider {

    Logger log = LogManager.getLogger(JwtProvider.class);

    @Value("${ead.auth.jwtKey}")
    private String jwtKey;

    public String getSubjectJwt(String token) {
        return Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getClaimNameJwt(String token, String claimName){
        return Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token).getBody().get(claimName).toString();
    }


    public boolean validateJwt(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e){
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e){
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }


}
