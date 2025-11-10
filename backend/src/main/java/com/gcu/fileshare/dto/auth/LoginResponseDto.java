package com.gcu.fileshare.dto.auth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginResponseDto
{
    private String jwtToken;
    
    public LoginResponseDto(String jwtToken)
    {
        log.info("[LoginResponseDto] Parameterizedf constructor called.");
        
        this.jwtToken = jwtToken;
    }

    public String getJwtToken()
    {
        return this.jwtToken;
    }

    public void setJwtToken(String jwtToken)
    {
        this.jwtToken = jwtToken;
    }
}