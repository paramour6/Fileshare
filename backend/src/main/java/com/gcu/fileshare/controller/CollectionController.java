package com.gcu.fileshare.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gcu.fileshare.config.AuthenticatedController;
import com.gcu.fileshare.dto.CollectionDto;
import com.gcu.fileshare.dto.UserDto;
import com.gcu.fileshare.service.CollectionService;
import com.gcu.fileshare.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for handling collection related requests
 */
@RestController
@Slf4j
public class CollectionController extends AuthenticatedController
{
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private UserService userService;

    /** 
     * @param userId ID of user to find collections for
     * @param visibility Public/Private collections
     * @return List of collection DTOs
     */
    @GetMapping("/collections")
    public ResponseEntity<?> findAllCollections(@RequestParam(required=false) Long userId, @RequestParam(required=false) Boolean visibility)
    {
        try
        {
            if(userId == null && visibility == null)
                {
                log.info("[CollectionController] Getting all collections.");

                return ResponseEntity.ok(collectionService.findAll());
            }
            else if(visibility == null)
            {
                log.info("[CollectionController] Getting collections by user" + userId);

                Optional<UserDto> user = userService.findUserById(userId);
                
                if(user.isPresent())
                {
                    return ResponseEntity.ok(collectionService.findAllByUser(user.get()));
                }
                else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID " + userId + " does not exist!");
            }
            else
            {
                log.info("[CollectionController] Getting collections by visibility of " + visibility);

                return ResponseEntity.ok(collectionService.findAllByVisibility(visibility));
            }
        }
        catch(Exception e)
        {
            log.error("[CollectionController] Exception caught in UserService.findAllCollections!");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error getting all collections!");
        }
    }

    /** 
     * @param id ID of collection to find
     * @return Collection with matching ID
     */
    @GetMapping("/collections/{id}")
    public ResponseEntity<?> findCollectionById(@PathVariable long id)
    {
        log.info("[CollectionController] Getting collection by id " + id);

        try
        {
            Optional<CollectionDto> collection = collectionService.findCollectionById(id);

            if(collection.isPresent())
            {
                return ResponseEntity.ok(collection.get());
            }
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Collection id " + id + " does not exist!");
        }
        catch(Exception e)
        {
            log.error("[CollectionController] Exception caught in CollectionController.findCollectionById!");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error getting collection by collection id!");
        }
    }

    /** 
     * @param collectionDto The collection to be created
     * @return The created collection
     */
    @PostMapping("/collections")
    public ResponseEntity<?> createCollection(@RequestBody CollectionDto collectionDto)
    {
        log.info("[CollectionController] Creating collection.");

        try
        {
            Optional<CollectionDto> collection = collectionService.createCollection(collectionDto);

            if(collection.isPresent())
            {
                return ResponseEntity.ok(collection.get());
            }
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid collection creation request!");
        }
        catch(Exception e)
        {
            log.error("[CollectionController] Exception caught in CollectionController.createCollection!");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error creating the collection!");
        }
    }

    /** 
     * @param id ID of collection to upload to
     * @param files List of files to upload
     * @return A 200 OK status
     */
    @PostMapping(value="/collections/{id}/files", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFilesToCollection(@PathVariable long id, @RequestParam("files") List<MultipartFile> files)
    {
        log.info("[CollectionController] Uploading files to collection " + id);

        try
        {
            if(collectionService.uploadFilesToCollection(id, files))
            {
                return ResponseEntity.ok("Files added to collection");
            }
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file upload request!");
        }
        catch(Exception e)
        {
            log.error("[CollectionController] Exception caught in CollectionController.uploadFilesToCollection!");
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error uploading files to the collection!");
        }
    }

    /** 
     * @param id ID of collection
     * @param download Indicate a download or not
     * @param filename Filename to download
     * @return The downloaded file DTOs or octet streams
     */
    @GetMapping("/collections/{id}/files")
    public ResponseEntity<?> getFilesFromCollection(@PathVariable long id, @RequestParam(required=false) Boolean download, @RequestParam(required=false) String filename)
    {
        if(download == null)
        {
            log.info("[CollectionController] Getting files from collection " + id);
    
            try
            {
                return ResponseEntity.ok(collectionService.getCollectionFiles(id));
            }
            catch(Exception e)
            {
                log.info("[CollectionController] Exception caught in CollectionController.getFilesFromCollection!");
    
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error getting files from the collection!");
            }
        }
        else if(filename == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A filename to download was not provided!");

        log.info("[CollectionController] Downloading file from collection " + id);

        try
        {
            byte[] fileBytes = collectionService.getCollectionFileBytes(id, filename);
            String header = "attachment; filename=\"" + filename + "\"";

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).header(HttpHeaders.CONTENT_DISPOSITION, header).body(fileBytes);
        }
        catch(FileNotFoundException e)
        {
            log.error("[CollectionController] FileNotFoundException caught in CollectionController.getFilesFromCollection!");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested file was not found in the collection!");
        }
        catch(Exception e)
        {
            log.error("[CollectionController] Exception caught in CollectionController.getFilesFromCollection!");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There was an error downloading the requested file!");
        }
    }
}