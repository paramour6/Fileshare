import { AxiosRequestConfig } from "axios";
import CollectionDto from "../models/CollectionDto";
import FileDto from "../models/FileDto";
import ApiService from "./ApiService";

export default class CollectionService
{
    private backend = ApiService.getInstance();

    public async getAllCollections(visibility?: boolean | undefined, byUserId?: number | undefined): Promise<CollectionDto[]>
    {
        console.log("[CollectionService] Getting all collections.");

        try
        {
            const response = await this.backend.get("/collections");

            if(response.status !== 200)
            {
                throw new Error();
            }

            return response.data;
        }
        catch(error)
        {
            console.error("[CollectionService] Error getting all collections!");

            return [];
        }
    }

    public async getAllCollectionsByVisibility(visibility: boolean): Promise<CollectionDto[]>
    {
        console.log("[CollectionService] Getting all collections by visibility of " + visibility);

        try
        {
            if(visibility)
            {
                const response = await this.backend.get("/collections?visibility=true");

                if(response.status !== 200) throw new Error();

                return response.data;
            }
            else
            {
                const response = await this.backend.get("/collections?visibility=false");

                if(response.status !== 200) throw new Error();

                return response.data;
            }
        }
        catch(error)
        {
            console.error("[CollectionService] Error getting collections by visibility!");

            return [];
        }
    }

    public async getAllCollectionsByUser(userId: number): Promise<CollectionDto[]>
    {
        console.log("[CollectionService] Getting all collections by user " + userId);

        try
        {
            const response = await this.backend.get("/collections?userId=" + userId);

            if(response.status !== 200) throw new Error();

            return response.data;
        }
        catch(error)
        {
            console.error("[CollectionService] Error getting collections by user!");

            return [];
        }
    }

    public async getCollectionFiles(collection: CollectionDto): Promise<FileDto[]>
    {
        console.log("[CollectionService] Getting files from collection " + collection.id);

        try
        {
            const response = await this.backend.get("/collections/" + collection.id + "/files");

            if(response.status !== 200)
            {
                throw new Error();
            }

            return response.data;
        }
        catch(error)
        {
            console.error("[CollectionService] Error getting collection files!");

            return [];
        }
    }

    public async createCollection(collection: CollectionDto): Promise<CollectionDto | undefined>
    {
        console.log("[CollectionService] Creating collection.");

        try
        {
            const response = await this.backend.post("/collections", collection);

            if(response.status !== 200)
            {
                throw new Error();
            }

            return response.data;
        }
        catch(error)
        {
            console.error("[CollectionService] Error creating collection!");

            return undefined;
        }
    }

    public async downloadFile(file: FileDto): Promise<string | null>
    {
        console.log("[CollectionService] Downloading file from collection " + file.collectionId);

        try
        {
            const config: AxiosRequestConfig = {
                params: {download: true, filename: file.fileName},
                responseType: "blob",
                headers: {"Accept": "application/octet-stream"}
            }
            const response = await this.backend.get("/collections/" + file.collectionId + "/files", config);

            if(response.status !== 200)
            {
                throw new Error();
            }

            const blob: Blob = new Blob([response.data], {type: response.headers["Content-Type"]?.toString()})
            return URL.createObjectURL(blob);
        }
        catch(error)
        {
            console.error("[CollectionService] Error downloading file!");

            return null;
        }
    }

    public async uploadFiles(collectionId: number, formData: FormData): Promise<boolean>
    {
        console.log("[CollectionService] Uploading files to collection " + collectionId);

        try
        {
            const response = await this.backend.post("/collections/" + collectionId + "/files", formData);

            if(response.status !== 200)
            {
                throw new Error();
            }

            return true;
        }
        catch(error)
        {
            console.error("[CollectionService] Error uploading files!");

            return false;
        }
    }
}