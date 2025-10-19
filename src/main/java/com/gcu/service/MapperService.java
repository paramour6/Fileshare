package com.gcu.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.gcu.dao.entity.*;
import com.gcu.dto.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
@Slf4j
public class MapperService
{
    public static UserEntity fromDto(UserDto userDto, String passwordHash)
    {
        log.info("MapperService.fromDto(UserDto): Mapping UserDto to UserEntity.");

        UserEntity userEntity = new UserEntity(userDto.getId());

        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmailAddress(userDto.getEmailAddress());
        userEntity.setPasswordHash(passwordHash);

        return userEntity;
    }

    public static CollectionEntity fromDto(CollectionDto collectionDto)
    {
        log.info("MapperService.fromDto(CollectionDto): Mapping CollectionDto to CollectionEntity.");

        CollectionEntity collectionEntity = new CollectionEntity(collectionDto.getId());

        collectionEntity.setUser(new UserEntity(collectionDto.getUserId()));
        collectionEntity.setVisibility(collectionDto.getVisibility());

        try
        {
            collectionEntity.setCreatedAt(LocalDate.parse(collectionDto.getCreatedAt()));

            return collectionEntity;
        }
        catch (DateTimeParseException e)
        {
            log.error("MapperService.fromDto(CollectionDto): Failed to parse createdAt string into LocalDate: ", e);

            throw new IllegalArgumentException("Invalid string date format for CollectionDto.createdAt!");
        }
    }

    public static FileEntity fromDto(FileDto fileDto, String hashedFileName)
    {
        log.info("MapperService.fromDto(FileDto): Mapping FileDto to FileEntity.");
        
        FileEntity fileEntity = new FileEntity(fileDto.getId());

        fileEntity.setCollection(new CollectionEntity(fileDto.getCollectionId()));
        fileEntity.setFileName(fileDto.getFileName());
        fileEntity.setFileType(fileDto.getFileType());
        fileEntity.setFileSizeKb(fileDto.getFileSizeKb());
        fileEntity.setHashedFileName(hashedFileName);

        return fileEntity;
    }

    public static SharedCollectionEntity fromDto(SharedCollectionDto sharedCollectionDto)
    {
        log.info("MapperService.fromDto(SharedCollectionDto): Mapping SharedCollectionDto to SharedCollectionEntity.");

        SharedCollectionEntity sharedCollectionEntity = new SharedCollectionEntity();

        sharedCollectionEntity.setCollection(new CollectionEntity(sharedCollectionDto.getCollectionId()));
        sharedCollectionEntity.setSharedWithUser(new UserEntity(sharedCollectionDto.getSharedWithUserId()));

        return sharedCollectionEntity;
    }

    public static UserDto toDto(UserEntity userEntity)
    {
        log.info("MapperService.toDto(UserEntity): Mapping UserEntity to UserDto.");

        UserDto userDto = new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getEmailAddress());

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

        SharedCollectionDto sharedCollectionDto = new SharedCollectionDto(sharedCollectionEntity.getCollection().getId(),
                                                                          sharedCollectionEntity.getSharedWithUser().getId());

        return sharedCollectionDto;
    }
}