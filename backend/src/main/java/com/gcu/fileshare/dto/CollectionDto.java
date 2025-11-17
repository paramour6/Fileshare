package com.gcu.fileshare.dto;

import lombok.extern.slf4j.Slf4j;

/**
 * DTO for a collection
 */
@Slf4j
public class CollectionDto
{
    private long id;
    private long userId;
    private boolean visibility;
    private String createdAt;

    public CollectionDto()
    {
        log.info("[CollectionDto] No argument constructor called.");

        this.id = -1;
        this.userId = -1;
        this.visibility = false;
        this.createdAt = "";
    }

    public CollectionDto(long id, long userId, boolean visibility, String createdAt)
    {
        log.info("[CollectionDto] Parameterized constructor called.");

        this.id = id;
        this.userId = userId;
        this.visibility = visibility;
        this.createdAt = createdAt;
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
     * @return long
     */
    public long getUserId()
    {
        return this.userId;
    }

    /** 
     * @param userId
     */
    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    /** 
     * @return boolean
     */
    public boolean getVisibility()
    {
        return this.visibility;
    }
    
    /** 
     * @param visibility
     */
    public void setVisibility(boolean visibility)
    {
        this.visibility = visibility;
    }

    /** 
     * @return String
     */
    public String getCreatedAt()
    {
        return this.createdAt;
    }

    /** 
     * @param createdAt
     */
    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }
}