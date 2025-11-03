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

    public BlobContainerClient createContainer(String containerName)
    {
        return blobServiceClient.createBlobContainer(containerName);
    }

    public BlobContainerClient getContainer(String containerName)
    {
        return blobServiceClient.getBlobContainerClient(containerName);
    }

    public void deleteContainer(String containerName)
    {
        blobServiceClient.deleteBlobContainer(containerName);
    }

    public void uploadBlob(BlobContainerClient blobContainer, MultipartFile file) throws IOException
    {
        blobContainer.getBlobClient(file.getOriginalFilename()).upload(BinaryData.fromBytes(file.getBytes()));
    }

    public List<BlobClient> getBlobsFromContainer(BlobContainerClient blobContainer)
    {
        List<BlobClient> blobClientList = new ArrayList<>();

        blobContainer.listBlobs().forEach(blob -> 
            blobClientList.add(blobContainer.getBlobClient(blob.getName())));

        return blobClientList;
    }

    public BlobClient getBlobFromContainer(BlobContainerClient blobContainer, String blobName) throws FileNotFoundException
    {
        if(blobContainer.getBlobClient(blobName).exists())
        {
            return blobContainer.getBlobClient(blobName);
        }

        throw new FileNotFoundException();
    }

    public byte[] getBlobBytes(BlobClient blobClient)
    {
        return blobClient.downloadContent().toBytes();
    }
}