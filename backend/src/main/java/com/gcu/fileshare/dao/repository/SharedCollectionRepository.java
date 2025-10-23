package com.gcu.fileshare.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gcu.fileshare.dao.entity.SharedCollectionEntity;
import com.gcu.fileshare.dao.entity.CollectionEntity;
import com.gcu.fileshare.dao.entity.UserEntity;
import org.springframework.data.domain.Sort;
import java.util.List;

@Repository
public interface SharedCollectionRepository extends JpaRepository<SharedCollectionEntity, Long>
{
    List<UserEntity> findUsersByCollection(CollectionEntity collection);
    List<UserEntity> findUsersByCollection(CollectionEntity collection, Sort sort);

    List<CollectionEntity> findCollectionsBySharedWithUser(UserEntity sharedWithUser);
}