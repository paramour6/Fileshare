package com.gcu.dto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SharedCollectionDto
{
    private long collectionId;
    private long sharedWithUserId;

    public SharedCollectionDto()
    {
        log.info("SharedCollectionDto: No argument constructor called.");

        this.collectionId = -1;
        this.sharedWithUserId = -1;
    }

    public SharedCollectionDto(long collectionId, long sharedWithUserId)
    {
        log.info("SharedCollectionDto: Parameterized constructor called.");

        this.collectionId = collectionId;
        this.sharedWithUserId = sharedWithUserId;
    }

    public long getCollectionId()
    {
        return this.collectionId;
    }
    
    public void setCollectionId(long collectionId)
    {
        this.collectionId = collectionId;
    }

    public long getSharedWithUserId()
    {
        return this.sharedWithUserId;
    }

    public void setSharedWithUserId(long sharedWithUserId)
    {
        this.sharedWithUserId = sharedWithUserId;
    }
}