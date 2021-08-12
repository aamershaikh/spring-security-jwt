package com.aamer.springsecurity.jwt.util;

/*
Util class for
    1. generating the jwt token based on Userdetails that is passed
    2. creating a jwt token along with the claims, subject, IssuedAtTime, expiration time and sign algorithm
    3. extracting the claims from the jwt token
    4. check validity of token, check if token is expired
    5. extract username from the token
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    public final String SECRET_KEY = "secret";

    // generate token
    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        String username = userDetails.getUsername();
        return createToken(claims,username);
    }

    // create a new jwt token
    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    // validate the jwt token
    public Boolean validateToken(String token, UserDetails userdetails) {
        final String username = extractUsername(token);
        return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
    }

    // check if token is expired
    private boolean isTokenExpired(String token) {
        return extractTokenExpiration(token).before(new Date());
    }

    // extract token expiry time from claims
    private Date extractTokenExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // extract username from claims
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    // extract claims
    private <T>T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // get the body from token
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

}
