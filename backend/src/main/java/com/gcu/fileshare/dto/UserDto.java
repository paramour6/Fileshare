package com.gcu.fileshare.dto;

import lombok.extern.slf4j.Slf4j;

/**
 * DTO for a user
 */
@Slf4j
public class UserDto
{
    private long id;
    private String username;
    private String emailAddress;
    private String password;

    public UserDto()
    {
        log.info("UserDto: No argument constructor called.");

        this.id = -1;
        this.username = "";
        this.emailAddress = "";
        this.password = "";
    }

    public UserDto(long id, String username, String emailAddress, String password)
    {
        log.info("[UserDto] Parameterized constructor called.");

        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    /** 
     * @return long
     */
    public long getId()
    {
        return this.id;
    }

    /** 
     * @param id
     */
    public void setId(long id)
    {
        this.id = id;
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