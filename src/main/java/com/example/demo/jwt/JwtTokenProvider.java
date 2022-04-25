package com.example.demo.jwt;

import com.example.demo.repository.CustormDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String JWT_SECRET = "vandv";
    private final long JWT_EXPIRATION = 604800000L;

    public String generateToken(CustormDetailService userDetails){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(Long.toString(userDetails.getUser().getId()))
                .setExpiration(expiryDate)
                .setIssuedAt(new Date()).
                signWith(SignatureAlgorithm.HS512,JWT_SECRET)
                .compact();
    }
    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
}
