package com.gcu.fileshare.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for handling Azure related BLOB operations
 */
@Service
@Slf4j
public class AzureService
{
    private final String AZURE_CONNECTION_STRING;

    private final BlobServiceClient blobServiceClient;

    public AzureService(@Value("${spring.cloud.azure.storage.fileshare.connection-string}") String connStr)
    {
        this.AZURE_CONNECTION_STRING = connStr;
        this.blobServiceClient = new BlobServiceClientBuilder().connectionString(AZURE_CONNECTION_STRING).buildClient();
    }

    /** 
     * @param containerName Name of container to create
     * @return BlobContainerClient
     */
    public BlobContainerClient createContainer(String containerName)
    {
        log.info("[AzureService] Creating container " + containerName);

        return blobServiceClient.createBlobContainer(containerName);
    }

    /** 
     * @param containerName Name of container to get
     * @return BlobContainerClient
     */
    public BlobContainerClient getContainer(String containerName)
    {
        log.info("[AzureService] Getting container " + containerName);

        return blobServiceClient.getBlobContainerClient(containerName);
    }

    /** 
     * @param containerName Name of container to delete
     */
    public void deleteContainer(String containerName)
    {
        log.info("[AzureService] Deleting container " + containerName);
        
        blobServiceClient.deleteBlobContainer(containerName);
    }

    /** 
     * @param blobContainer Container to upload to
     * @param file File to upload
     * @throws IOException Thrown if error occurs while uploading
     */
    public void uploadBlob(BlobContainerClient blobContainer, MultipartFile file) throws IOException
    {
        log.info("[AzureService] Uploading blob to " + blobContainer.getBlobContainerName());

        blobContainer.getBlobClient(file.getOriginalFilename()).upload(BinaryData.fromBytes(file.getBytes()));
    }

    /** 
     * @param blobContainer Container to get blobs from
     * @return List<BlobClient>
     */
    public List<BlobClient> getBlobsFromContainer(BlobContainerClient blobContainer)
    {
        log.info("[AzureService] Getting blobs from " + blobContainer.getBlobContainerName());

        List<BlobClient> blobClientList = new ArrayList<>();

        blobContainer.listBlobs().forEach(blob -> 
            blobClientList.add(blobContainer.getBlobClient(blob.getName())));

        return blobClientList;
    }

    /** 
     * @param blobContainer Container to get blob from
     * @param blobName Blob to get
     * @return BlobClient
     * @throws FileNotFoundException Thrown if blob (file) does not exist
     */
    public BlobClient getBlobFromContainer(BlobContainerClient blobContainer, String blobName) throws FileNotFoundException
    {
        log.info("[AzureService] Getting blob from " + blobContainer.getBlobContainerName());

        if(blobContainer.getBlobClient(blobName).exists())
        {
            return blobContainer.getBlobClient(blobName);
        }

        throw new FileNotFoundException();
    }

    /** 
     * @param blobClient Blob to get bytes from
     * @return byte[]
     */
    public byte[] getBlobBytes(BlobClient blobClient)
    {
        log.info("[AzureService] Getting blob bytes.");
        
        return blobClient.downloadContent().toBytes();
    }
}