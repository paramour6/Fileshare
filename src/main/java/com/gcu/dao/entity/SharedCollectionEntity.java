package com.gcu.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.ConstraintMode;

@Entity
public class SharedCollectionEntity
{
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

    public CollectionEntity getCollection()
    {
        return this.collection;
    }

    public void setCollection(CollectionEntity collection)
    {
        this.collection = collection;
    }

    public UserEntity getSharedWithUser()
    {
        return this.sharedWithUser;
    }

    public void setSharedWithUser(UserEntity sharedWithUser)
    {
        this.sharedWithUser = sharedWithUser;
    }
}