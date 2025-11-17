package com.gcu.fileshare.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;

/**
 * The entity class for a shared collection
 */
@Entity
@Table(name="SharedCollections")
public class SharedCollectionEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="shared_collection_id")
    private long id;

    @ManyToOne
    @JoinColumn(name="collection_id", nullable=false, referencedColumnName="collection_id",
                foreignKey=@ForeignKey(
                    name="fk_shared_collection_id", foreignKeyDefinition="FOREIGN KEY(collection_id) REFERENCES Collections(collection_id) ON DELETE CASCADE ON UPDATE CASCADE",
                    value=ConstraintMode.CONSTRAINT
                    )
                )
    private CollectionEntity collection;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, referencedColumnName="user_id",
                foreignKey=@ForeignKey(
                    name="fk_shared_with_user_id", foreignKeyDefinition="FOREIGN KEY(user_id) REFERENCES Users(user_id) ON DELETE CASCADE ON UPDATE CASCADE",
                    value=ConstraintMode.CONSTRAINT
                    )
                )
    private UserEntity sharedWithUser;

    public SharedCollectionEntity() {}

    public SharedCollectionEntity(CollectionEntity collection, UserEntity sharedWithUser)
    {
        this.collection = collection;
        this.sharedWithUser = sharedWithUser;
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
     * @return CollectionEntity
     */
    public CollectionEntity getCollection()
    {
        return this.collection;
    }

    /** 
     * @param collection
     */
    public void setCollection(CollectionEntity collection)
    {
        this.collection = collection;
    }

    /** 
     * @return UserEntity
     */
    public UserEntity getSharedWithUser()
    {
        return this.sharedWithUser;
    }

    /** 
     * @param sharedWithUser
     */
    public void setSharedWithUser(UserEntity sharedWithUser)
    {
        this.sharedWithUser = sharedWithUser;
    }
}