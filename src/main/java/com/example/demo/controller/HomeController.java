package com.example.demo.controller;

import com.example.demo.entiy.User;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.payload.LoginReponse;
import com.example.demo.payload.LoginRequest;
import com.example.demo.repository.CustormDetailService;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authen")
public class HomeController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
        CustormDetailService userDetails = (CustormDetailService) authentication.getPrincipal();
        String jwt =jwtTokenProvider.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }
    @GetMapping("/add")
    public String addNewAccount(){
        User user = new User(null,"vandv",passwordEncoder.encode("vandv"));
        userRepository.save(user);
        return null;
    }
}
