package com.gcu.fileshare.dto.auth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginRequestDto
{
    private String username;
    private String password;
    
    public LoginRequestDto(String username, String password)
    {
        log.info("[LoginRequestDto] Parameterized constructor called");

        this.username = username;
        this.password = password;
    }

    public LoginRequestDto()
    {
        log.info("[LoginRequestDto] Default constructor called");
        
        this.username = "";
        this.password = "";
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
