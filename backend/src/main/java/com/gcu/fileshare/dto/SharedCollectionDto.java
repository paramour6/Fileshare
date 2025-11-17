package com.gcu.fileshare.dto;

import lombok.extern.slf4j.Slf4j;

/**
 * DTO for a shared collection
 */
@Slf4j
public class SharedCollectionDto
{
    private long id;
    private long collectionId;
    private long sharedWithUserId;

    public SharedCollectionDto()
    {
        log.info("[SharedCollectionDto] No argument constructor called.");

        this.id = -1;
        this.collectionId = -1;
        this.sharedWithUserId = -1;
    }

    public SharedCollectionDto(long id, long collectionId, long sharedWithUserId)
    {
        log.info("[SharedCollectionDto] Parameterized constructor called.");

        this.id = id;
        this.collectionId = collectionId;
        this.sharedWithUserId = sharedWithUserId;
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
    public long getCollectionId()
    {
        return this.collectionId;
    }
    
    /** 
     * @param collectionId
     */
    public void setCollectionId(long collectionId)
    {
        this.collectionId = collectionId;
    }

    /** 
     * @return long
     */
    public long getSharedWithUserId()
    {
        return this.sharedWithUserId;
    }

    /** 
     * @param sharedWithUserId
     */
    public void setSharedWithUserId(long sharedWithUserId)
    {
        this.sharedWithUserId = sharedWithUserId;
    }
}