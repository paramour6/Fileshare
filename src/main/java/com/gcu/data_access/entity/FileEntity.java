package com.gcu.data_access.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.ConstraintMode;

@Entity
public class FileEntity 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="file_id")
    private long id;

    @ManyToOne
    @JoinColumn(name="collection_id", nullable=false, referencedColumnName="collection_id",
                foreignKey=@ForeignKey(
                    name="fk_collection_id", foreignKeyDefinition="FOREIGN KEY(collection_id) REFERENCES Collections(collection_id) ON DELETE CASCADE ON UPDATE CASCADE",
                    value=ConstraintMode.CONSTRAINT
                    )
                )
    private CollectionEntity collection;

    @Column(name="file_name", nullable=false)
    private String fileName;

    @Column(name="file_type", nullable=false)
    private String fileType;

    @Column(name="file_size_kb", nullable=false)
    private int fileSizeKb;

    @Column(name="hashed_file_name", nullable=false, unique=true)
    private String hashedFileName;

    @Column(name="hash_salt", nullable=false)
    private String hashSalt;

    public long getId() 
    {
        return this.id;
    }

    public void setId(long id) 
    {
        this.id = id;
    }

    public CollectionEntity getCollection() 
    {
        return this.collection;
    }

    public void setCollection(CollectionEntity collectionEntity) 
    {
        this.collection = collectionEntity;
    }

    public String getFileName() 
    {
        return this.fileName;
    }

    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileType() 
    {
        return this.fileType;
    }

    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public int getFileSizeKb() 
    {
        return this.fileSizeKb;
    }

    public void setFileSizeKb(int fileSizeKb) 
    {
        this.fileSizeKb = fileSizeKb;
    }

    public String getHashedFileName() 
    {
        return this.hashedFileName;
    }

    public void setHashedFileName(String hashedFileName) 
    {
        this.hashedFileName = hashedFileName;
    }

    public String getHashSalt() 
    {
        return this.hashSalt;
    }

    public void setHashSalt(String hashSalt) 
    {
        this.hashSalt = hashSalt;
    }
}