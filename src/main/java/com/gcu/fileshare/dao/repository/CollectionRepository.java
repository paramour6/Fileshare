package com.gcu.fileshare.dao.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import com.gcu.fileshare.dao.entity.CollectionEntity;
import com.gcu.fileshare.dao.entity.UserEntity;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long>
{
    List<CollectionEntity> findAllByUser(UserEntity user);
    List<CollectionEntity> findAllByUser(UserEntity user, Sort sort);

    List<CollectionEntity> findAllByVisibility(boolean visibility);
    List<CollectionEntity> findAllByVisibility(boolean visibility, Sort sort);
}