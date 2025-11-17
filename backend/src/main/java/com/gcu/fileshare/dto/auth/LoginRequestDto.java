package com.gcu.fileshare.dto.auth;

import lombok.extern.slf4j.Slf4j;

/**
 * DTO for a login request
 */
@Slf4j
public class LoginRequestDto
{
    private String username;
    private String password;
    
    public LoginRequestDto(String username, String password)
    {
        log.info("[LoginRequestDto] Parameterized constructor called.");

        this.username = username;
        this.password = password;
    }

    public LoginRequestDto()
    {
        log.info("[LoginRequestDto] Default constructor called.");
        
        this.username = "";
        this.password = "";
    }

    /** 
     * @return String
     */
    public String getUsername()
    {
        return this.username;
    }

    /** 
     * @param username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /** 
     * @return String
     */
    public String getPassword()
    {
        return this.password;
    }

    /** 
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
}
