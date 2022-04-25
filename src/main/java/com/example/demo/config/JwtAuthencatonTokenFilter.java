package com.example.demo.config;

import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.CustormDetailService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthencatonTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = getJwtFromRequest(request);
        if (jwtToken !=null){
            Long userId = jwtTokenProvider.getUserIdFromJWT(jwtToken);
            UserDetails userDetails = userService.loadUserById(userId);

        }
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
