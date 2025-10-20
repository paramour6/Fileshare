package com.gcu.fileshare.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Table(name="Collections")
public class CollectionEntity
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="collection_id")
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, referencedColumnName="user_id",
                foreignKey=@ForeignKey(
                    name="fk_user_id", foreignKeyDefinition="FOREIGN KEY(user_id) REFERENCES Users(user_id) ON DELETE CASCADE ON UPDATE CASCADE",
                    value=ConstraintMode.CONSTRAINT
                    )
                )
    private UserEntity user;

    @Column(name="visibility", nullable=false)
    private boolean visibility;

    @Column(name="created_at", nullable=false)
    private LocalDate createdAt;

    public CollectionEntity() {}

    public CollectionEntity(UserEntity user, boolean visibility, LocalDate createdAt)
    {
        this.user = user;
        this.visibility = visibility;
        this.createdAt = createdAt;
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public UserEntity getUser()
    {
        return this.user;
    }

    public void setUser(UserEntity user)
    {
        this.user = user;
    }

    public boolean getVisibility()
    {
        return this.visibility;
    }

    public void setVisibility(boolean visibility)
    {
        this.visibility = visibility;
    }

    public LocalDate getCreatedAt()
    {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt)
    {
        this.createdAt = createdAt;
    }
}