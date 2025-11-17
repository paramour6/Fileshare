package com.gcu.fileshare.service.util;

import com.gcu.fileshare.dao.entity.*;
import com.gcu.fileshare.dto.*;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for mapping entities to DTOs
 */
@Service
@Slf4j
public class MapperService
{
    /** 
     * @param userEntity Entity to map
     * @return UserDto
     */
    public static UserDto toDto(UserEntity userEntity)
    {
        log.info("[MapperService] Mapping UserEntity to UserDto.");

        UserDto userDto = new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getEmailAddress(), "");

        return userDto;
    }

    /** 
     * @param collectionEntity Entity to map
     * @return CollectionDto
     */
    public static CollectionDto toDto(CollectionEntity collectionEntity)
    {
        log.info("[MapperService] Mapping CollectionEntity to CollectionDto.");

        CollectionDto collectionDto = new CollectionDto(collectionEntity.getId(),
                                                        collectionEntity.getUser().getId(),
                                                        collectionEntity.getVisibility(),
                                                        collectionEntity.getCreatedAt().toString());

        return collectionDto;
    }

    /** 
     * @param fileEntity Entity to map
     * @return FileDto
     */
    public static FileDto toDto(FileEntity fileEntity)
    {
        log.info("[MapperService] Mapping FileEntity to FileDto.");

        FileDto fileDto = new FileDto(fileEntity.getId(),
                                      fileEntity.getCollection().getId(),
                                      fileEntity.getFileName(),
                                      fileEntity.getFileType(),
                                      fileEntity.getFileSizeKb());

        return fileDto;
    }

    /** 
     * @param sharedCollectionEntity Entity to map
     * @return SharedCollectionDto
     */
    public static SharedCollectionDto toDto(SharedCollectionEntity sharedCollectionEntity)
    {
        log.info("[MapperService] Mapping SharedCollectionEntity to SharedCollectionDto.");

        SharedCollectionDto sharedCollectionDto = new SharedCollectionDto(sharedCollectionEntity.getId(), sharedCollectionEntity.getCollection().getId(),
                                                                          sharedCollectionEntity.getSharedWithUser().getId());

        return sharedCollectionDto;
    }
}