package com.gcu.fileshare.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

/**
 * The entity class for a user
 */
@Entity
@Table(name="Users")
public class UserEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private long id;

    @Column(name="username", nullable=false, unique=true)
    private String username;

    @Column(name="email_address", nullable=false, unique=true)
    private String emailAddress;

    @Column(name="password_hash", nullable=false)
    private String passwordHash;

    public UserEntity() {}

    public UserEntity(String username, String emailAddress, String passwordHash)
    {
        this.username = username;
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
    }

    /** 
     * @return long
     */
    public long getId()
    {
        return id;
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
        return username;
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
    public String getPasswordHash()
    {
        return this.passwordHash;
    }

    /** 
     * @param passwordHash
     */
    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }
}