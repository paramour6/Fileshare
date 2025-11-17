package com.gcu.fileshare.config.jwt;

import java.util.Calendar;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;
import com.nimbusds.jose.util.StandardCharset;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

/**
 * Utlity class for handling JWTs
 */
@Component
@Slf4j
public class JwtUtility
{
    private static final int EXPIRATION_HRS = 1;
    private final SecretKey KEY;

    public JwtUtility()
    {
        String secret = System.getenv("JWT_SECRET");

        if(secret == null || secret.isBlank() || secret.isEmpty())
        {
            throw new IllegalStateException("JWT_SECRET environment variable was not set!");
        }

        this.KEY = Keys.hmacShaKeyFor(secret.getBytes(StandardCharset.UTF_8));
    }

    /** 
     * @param username Creates a JWT with the username as the subject
     * @return The created JWT
     */
    public String createToken(String username)
    {
        log.info("[JwtUtility] Creating JWT");
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, EXPIRATION_HRS);

        return Jwts.builder().subject(username).issuedAt(new Date()).expiration(calendar.getTime()).signWith(KEY).compact();
    }

    /** 
     * @param token JWT string to validate
     * @param username Username to check JWT subject against
     * @return boolean
     */
    public boolean validateToken(String token, String username)
    {
        log.info("[JwtUtility] Validating token");
        
        Claims claims = Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload();
        Date expirationDate = claims.getExpiration();
        String subjectUsername = claims.getSubject();

        return (expirationDate.after(new Date()) && subjectUsername.equals(username));
    }

    /** 
     * @param token JWT to parse username from
     * @return Parsed username
     */
    public String parseUsername(String token)
    {
        log.info("[JwtUtility] Parsing username from subject claim");
        
        return Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload().getSubject();
    }
}