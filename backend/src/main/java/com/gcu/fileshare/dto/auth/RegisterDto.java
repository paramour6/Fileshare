package com.gcu.fileshare.dto.auth;

import lombok.extern.slf4j.Slf4j;

/**
 * DTO for a register request
 */
@Slf4j
public class RegisterDto
{
    private String username;
    private String emailAddress;
    private String password;
    
    public RegisterDto(String username, String emailAddress, String password)
    {
        log.info("[RegisterDto] Parameterized constructor called.");
        
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
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
    public String getEmailAddress()
    {
        return this.emailAddress;
    }

    /** 
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
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