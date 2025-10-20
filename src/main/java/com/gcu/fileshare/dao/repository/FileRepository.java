package com.gcu.fileshare.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.gcu.fileshare.dao.entity.FileEntity;
import com.gcu.fileshare.dao.entity.CollectionEntity;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface FileRepository extends JpaRepository<FileEntity, Long>
{
    List<FileEntity> findAllByCollection(CollectionEntity collection);
    List<FileEntity> findAllByCollection(CollectionEntity collection, Sort sort);
}