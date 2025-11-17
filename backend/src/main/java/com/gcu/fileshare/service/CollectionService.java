package com.gcu.fileshare.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcu.fileshare.dao.entity.CollectionEntity;
import com.gcu.fileshare.dao.entity.UserEntity;
import com.gcu.fileshare.dto.CollectionDto;
import com.gcu.fileshare.dao.repository.CollectionRepository;
import com.gcu.fileshare.dto.UserDto;
import com.gcu.fileshare.service.util.MapperService;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.gcu.fileshare.dao.repository.UserRepository;
import com.gcu.fileshare.dto.FileDto;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for handling collection related operations
 */
@Service
@Slf4j
public class CollectionService
{
    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AzureService azureService;

    /** 
     * @return List<CollectionDto>
     */
    public List<CollectionDto> findAll()
    {
        log.info("[CollectionService] findAll() called.");

        List<CollectionEntity> collectionEntities = collectionRepository.findAll();

        return collectionEntities.stream().map(MapperService::toDto).collect(Collectors.toList());
    }

    /** 
     * @param id ID of collection to find
     * @return Optional<CollectionDto>
     */
    public Optional<CollectionDto> findCollectionById(long id)
    {
        log.info("[CollectionService] findCollectionById(long id) called.");

        Optional<CollectionEntity> collectionEntity = collectionRepository.findById(id);

        if(collectionEntity.isPresent())
        {
            return Optional.of(MapperService.toDto(collectionEntity.get()));
        }
        else return Optional.empty();
    }

    /** 
     * @param userDto User to find collections by
     * @return List<CollectionDto>
     */
    public List<CollectionDto> findAllByUser(UserDto userDto)
    {
        log.info("[CollectionService] findAllByUser(UserDto userDto) called.");

        List<CollectionEntity> collectionEntities = collectionRepository.findAllByUserId(userDto.getId());

        return collectionEntities.stream().map(MapperService::toDto).collect(Collectors.toList());
    }

    /** 
     * @param visibility Visiblity of collections to find
     * @return List<CollectionDto>
     */
    public List<CollectionDto> findAllByVisibility(boolean visibility)
    {
        log.info("[CollectionService] findAllByVisibility() called.");

        List<CollectionEntity> collectionEntities = collectionRepository.findAllByVisibility(visibility);

        return collectionEntities.stream().map(MapperService::toDto).collect(Collectors.toList());
    }

    /** 
     * @param collection Collection to create
     * @return Optional<CollectionDto>
     */
    public Optional<CollectionDto> createCollection(CollectionDto collection)
    {
        log.info("[CollectionService] Creating collection.");

        CollectionEntity collectionEntity = new CollectionEntity();
        Optional<UserEntity> user = userRepository.findById(collection.getUserId());

        if(user.isEmpty())
        {
            return Optional.empty();
        }

        collectionEntity.setUser(user.get());
        collectionEntity.setVisibility(collection.getVisibility());
        collectionEntity.setCreatedAt(LocalDate.now());
        collectionEntity = collectionRepository.save(collectionEntity);

        String containerName = "collection-" + collectionEntity.getId();
        azureService.createContainer(containerName);

        return Optional.of(MapperService.toDto(collectionEntity));
    }

    /** 
     * @param collectionId ID of collection to upload files to
     * @param fileList List of files to upload
     * @return True if successful, false if otherwise
     */
    public boolean uploadFilesToCollection(long collectionId, List<MultipartFile> fileList)
    {
        log.info("[CollectionService] Uploading list of files to collection.");

        try
        {
            BlobContainerClient blobContainer = azureService.getContainer("collection-" + collectionId);

            for(MultipartFile file : fileList)
            {
                azureService.uploadBlob(blobContainer, file);
            }

            return true;
        }
        catch(IOException e)
        {
            log.error("[CollectionService] IO Exception caught while uploading files to collection!");

            return false;
        }
    }

    /** 
     * @param collectionId ID of collection to get files from
     * @return List<FileDto>
     */
    public List<FileDto> getCollectionFiles(long collectionId)
    {
        log.info("[CollectionService] Getting collection files.");

    
        List<FileDto> fileDtos = new ArrayList<>();
        BlobContainerClient blobContainer = azureService.getContainer("collection-" + collectionId);
        List<BlobClient> blobClients = azureService.getBlobsFromContainer(blobContainer);

        for(BlobClient blob : blobClients)
        {
            FileDto fileDto = new FileDto();
            fileDto.setCollectionId(collectionId);
            fileDto.setFileName(blob.getBlobName());
            fileDto.setFileSizeKb((int)(blob.getProperties().getBlobSize() / 1000));

            fileDtos.add(fileDto);
        }

        return fileDtos;
       
    }

    /** 
     * @param collectionId ID of collection
     * @param filename File to get bytes from
     * @return byte[]
     * @throws FileNotFoundException Thrown if file does not exist
     */
    public byte[] getCollectionFileBytes(long collectionId, String filename) throws FileNotFoundException
    {
        log.info("[CollectionService] Getting collection file's bytes.");

        BlobContainerClient blobContainer = azureService.getContainer("collection-" + collectionId);

        return azureService.getBlobBytes(azureService.getBlobFromContainer(blobContainer, filename));
    }
}