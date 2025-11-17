package com.gcu.fileshare.dto;

import lombok.extern.slf4j.Slf4j;

/**
 * DTO for a file
 */
@Slf4j
public class FileDto
{
    private long id;
    private long collectionId;
    private String fileName;
    private String fileType;
    private int fileSizeKb;
    
    public FileDto()
    {
        log.info("[FileDto] No argument constructor called.");

        this.id = -1;
        this.collectionId = -1;
        this.fileName = "";
        this.fileType = "";
        this.fileSizeKb = -1;
    }
    
    public FileDto(long id, long collectionId, String fileName, String fileType, int fileSizeKb)
    {
        log.info("[FileDto] Parameterized constructor called.");

        this.id = id;
        this.collectionId = collectionId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSizeKb = fileSizeKb;
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
     * @return String
     */
    public String getFileName() 
    {
        return this.fileName;
    }

    /** 
     * @param fileName
     */
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    /** 
     * @return String
     */
    public String getFileType() 
    {
        return this.fileType;
    }

    /** 
     * @param fileType
     */
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    /** 
     * @return int
     */
    public int getFileSizeKb() 
    {
        return this.fileSizeKb;
    }

    /** 
     * @param fileSizeKb
     */
    public void setFileSizeKb(int fileSizeKb) 
    {
        this.fileSizeKb = fileSizeKb;
    }
}