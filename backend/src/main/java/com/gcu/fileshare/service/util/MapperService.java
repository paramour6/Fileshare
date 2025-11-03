package com.gcu.fileshare.service.util;

import com.gcu.fileshare.dao.entity.*;
import com.gcu.fileshare.dto.*;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MapperService
{
    public static UserDto toDto(UserEntity userEntity)
    {
        log.info("MapperService.toDto(UserEntity): Mapping UserEntity to UserDto.");

        UserDto userDto = new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getEmailAddress(), "");

        return userDto;
    }

    public static CollectionDto toDto(CollectionEntity collectionEntity)
    {
        log.info("MapperService.toDto(CollectionEntity): Mapping CollectionEntity to CollectionDto.");

        CollectionDto collectionDto = new CollectionDto(collectionEntity.getId(),
                                                        collectionEntity.getUser().getId(),
                                                        collectionEntity.getVisibility(),
                                                        collectionEntity.getCreatedAt().toString());

        return collectionDto;
    }

    public static FileDto toDto(FileEntity fileEntity)
    {
        log.info("MapperService.toDto(FileEntity): Mapping FileEntity to FileDto.");

        FileDto fileDto = new FileDto(fileEntity.getId(),
                                      fileEntity.getCollection().getId(),
                                      fileEntity.getFileName(),
                                      fileEntity.getFileType(),
                                      fileEntity.getFileSizeKb());

        return fileDto;
    }

    public static SharedCollectionDto toDto(SharedCollectionEntity sharedCollectionEntity)
    {
        log.info("MapperService.toDto(SharedCollectionEntity): Mapping SharedCollectionEntity to SharedCollectionDto.");

        SharedCollectionDto sharedCollectionDto = new SharedCollectionDto(sharedCollectionEntity.getId(), sharedCollectionEntity.getCollection().getId(),
                                                                          sharedCollectionEntity.getSharedWithUser().getId());

        return sharedCollectionDto;
    }
}