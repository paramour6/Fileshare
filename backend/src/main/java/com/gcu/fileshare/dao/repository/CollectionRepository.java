package com.gcu.fileshare.dao.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gcu.fileshare.dao.entity.CollectionEntity;

@Repository
public interface CollectionRepository extends JpaRepository<CollectionEntity, Long>
{
    List<CollectionEntity> findAllByUserId(Long userId);

    List<CollectionEntity> findAllByVisibility(boolean visibility);
}