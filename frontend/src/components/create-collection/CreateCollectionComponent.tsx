import React, { ChangeEvent, useState } from "react";
import "./CreateCollectionComponent.css";
import CollectionDto from "../../models/CollectionDto";
import ApiService from "../../services/ApiService";
import CollectionService from "../../services/CollectionService";
import { useNavigate } from "react-router-dom";

function CreateCollectionComponent(): React.ReactElement
{
    console.log("[CreateCollectionComponent] Rendering a create collection component.");

    const collectionService: CollectionService = new CollectionService();
    const navigate = useNavigate();
    const [checkedVisibility, setCheckedVisibility] = useState<string>("public");
    const [selectedFiles, setSelectedFiles] = useState<FileList | null>(null);
    var errorCreating: boolean = false;

    const handleFileChange = (e: ChangeEvent<HTMLInputElement>) =>
    {
        setSelectedFiles(e.target.files);
    }

    const handleSubmit = async(e: React.FormEvent<HTMLFormElement>) =>
    {
        console.log("[CreateCollectionComponent] Handling create collection submission.");

        e.preventDefault();

        const currentUserId: number = ApiService.getCurrentUserId();

        if((selectedFiles === null) || (selectedFiles.length === 0))
        {
            errorCreating = true;
            return;
        }

        const collection: CollectionDto = {
            id: -1,
            userId: currentUserId,
            visibility: checkedVisibility === "public",
            createdAt: "0000-00-00"
        };
        const created: CollectionDto | undefined = await collectionService.createCollection(collection);
        
        if(created === undefined)
        {
            errorCreating = true;
            return;
        }

        const formData = new FormData();

        for(let i = 0; i < selectedFiles.length; i++)
        {
            formData.append("files", selectedFiles[i]);
        }

        await collectionService.uploadFiles(created.id, formData).then((res) =>
        {
            if(res)
            {
                console.log("[CreateCollectionComponent] Collection submission successful. Redirecting.");
                
                errorCreating = false;
                navigate("/profile");
            }
            else errorCreating = true;
        });
    }
    
    return (
        <div className="create-collection card card--form">
            <h2>Create a Collection</h2>
            
            <form onSubmit={(e) => handleSubmit(e)}>
                <div>
                    <label>Visibility</label>
                    <div style={{display: 'flex', gap: 12, marginTop: 6}}>
                        <label style={{display: 'flex', gap: 6, alignItems: 'center'}}>
                            <input
                                type="radio"
                                name="visibility"
                                value="public"
                                checked={checkedVisibility === "public"}
                                onChange={(e) => setCheckedVisibility(e.target.value)}
                                required
                            />
                            <span className="muted">Public</span>
                        </label>

                        <label style={{display: 'flex', gap: 6, alignItems: 'center'}}>
                            <input
                                type="radio"
                                name="visibility"
                                value="private"
                                onChange={(e) => setCheckedVisibility(e.target.value)}
                                required
                            />
                            <span className="muted">Private</span>
                        </label>
                    </div>
                </div>

                <div>
                    <label>Upload Files</label>
                    <input type="file" multiple onChange={(e) => handleFileChange(e)} />

                    {selectedFiles && selectedFiles.length > 0 ? (
                        <div className="uploaded-list">
                            {Array.from(selectedFiles).map((f, i) => (
                                <div className="uploaded-item" key={i}>
                                    <div className="name">{f.name}</div>
                                    <div className="muted">{Math.round(f.size / 1024)} KB</div>
                                </div>
                            ))}
                        </div>
                    ) : null}
                </div>

                {errorCreating && <span>There was an error creating the collection!</span>}

                <div style={{marginTop: 12}}>
                    <button className="btn" type="submit">Create Collection</button>
                </div>
            </form>
        </div>
    )
}

export default CreateCollectionComponent;