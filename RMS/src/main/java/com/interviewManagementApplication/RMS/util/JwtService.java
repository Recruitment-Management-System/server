package com.interviewManagementApplication.RMS.util;

import com.interviewManagementApplication.RMS.constants.Consts;
import com.interviewManagementApplication.RMS.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    String secretKey = Consts.SecretKey;

    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    //check is this token is valid
    public boolean isValid(String token, UserDetails user){
        String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }


    //check whether is token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //get the expiration date of the token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //extract particular detail from payload
    public <T> T extractClaim(String token, Function<Claims,T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    //extract all the payload details form token
    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //generating token for a user
    public String generateToken(User user){
        String token = Jwts
                .builder()
                .subject(user.getUsername())
                .claim("role" , user.getRole().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(getSigninKey())
                .compact();

        return token;
    }

    public SecretKey getSigninKey(){
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
