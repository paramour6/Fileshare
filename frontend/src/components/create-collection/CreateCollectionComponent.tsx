import React, { ChangeEvent, useState } from "react";
import CollectionDto from "../../models/CollectionDto";
import ApiService from "../../services/ApiService";
import CollectionService from "../../services/CollectionService";

interface CreateCollectionProps
{
    collectionService: CollectionService
}

const CreateCollectionComponent: React.FC<CreateCollectionProps> = (props: CreateCollectionProps) =>
{
    const collectionService: CollectionService = props.collectionService;
    const [selectedFiles, setSelectedFiles] = useState<FileList | null>(null);
    
    const handleFileChange = (e: ChangeEvent<HTMLInputElement>) =>
    {
        setSelectedFiles(e.target.files);
    }

    const handleSubmit = async(e: React.FormEvent<HTMLFormElement>) =>
    {
        e.preventDefault();

        const currentUserId: number = await ApiService.getCurrentUserId();

        if((selectedFiles === null) || (selectedFiles.length === 0))
        {
            return;
        }

        const collection: CollectionDto = {
            id: -1,
            userId: currentUserId,
            visibility: true,
            createdAt: "0000-00-00"
        };
        const created: CollectionDto | undefined = await collectionService.createCollection(collection);
        
        if(created === undefined) return;

        const formData = new FormData();

        for(let i = 0; i < selectedFiles.length; i++)
        {
            formData.append("files", selectedFiles[i]);
        }

        await collectionService.uploadFiles(created.id, formData);
    }
    
    return (
        <form onSubmit={(e) => handleSubmit(e)}>
            <h4>Create A Collection:</h4>
            <label>Upload Files:</label>
            <input type="file" multiple onChange={(e) => handleFileChange(e)}/>

            <button type="submit">Upload</button>
        </form>
    )
}

export default CreateCollectionComponent;