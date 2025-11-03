package com.gcu.fileshare.dto.auth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegisterDto
{
    private String username;
    private String emailAddress;
    private String password;
    
    public RegisterDto(String username, String emailAddress, String password)
    {
        log.info("[RegisterDto] Parameterized constructor called");
        
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmailAddress()
    {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
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