package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginReponse {
    private String accessToken;
    private String tokenType="Bearer";
}
