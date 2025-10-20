package com.gcu.fileshare.dto;

import lombok.extern.slf4j.Slf4j;

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

    public UserDto(long id, String username, String emailAddress)
    {
        log.info("UserDto: Parameterized constructor called.");

        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = "";
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
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