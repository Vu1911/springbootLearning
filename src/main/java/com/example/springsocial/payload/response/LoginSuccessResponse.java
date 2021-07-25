package com.example.springsocial.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginSuccessResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public LoginSuccessResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
