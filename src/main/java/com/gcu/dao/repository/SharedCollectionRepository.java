package com.gcu.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gcu.dao.entity.SharedCollectionEntity;
import com.gcu.dao.entity.CollectionEntity;
import com.gcu.dao.entity.UserEntity;
import org.springframework.data.domain.Sort;
import java.util.List;

@Repository
public interface SharedCollectionRepository extends JpaRepository<SharedCollectionEntity, Long>
{
    List<UserEntity> findUsersByCollection(CollectionEntity collection);
    List<UserEntity> findUsersByCollection(CollectionEntity collection, Sort sort);

    List<CollectionEntity> findCollectionsByUser(UserEntity user);
}