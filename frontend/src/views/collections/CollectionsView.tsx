import React, { useEffect, useState } from "react";
import CollectionDto from "../../models/CollectionDto";
import CollectionService from "../../services/CollectionService";
import { Link } from "react-router-dom";
import FileListComponent from "../../components/file-list/FileListComponent";
import "./CollectionsView.css";
// import FileListComponent from "../../components/file-list/FileListComponent";

function CollectionsView(): React.ReactElement
{
    const [collectionList, setCollectionList] = useState<CollectionDto[]>([]);
    const collectionService: CollectionService = new CollectionService();

    useEffect(() =>
    {
        const getCollections = async() =>
        {
            setCollectionList(await collectionService.getAllCollectionsByVisibility(true));
        }

        getCollections();
    }, []);
    
    return (
        <div className="app-container">
            <div style={{display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: 10}}>
                <h1>Public Collections</h1>
                <Link to="/collections/create" className="btn">Create</Link>
            </div>

            <div className="card-grid">
            {
                collectionList.map((collection, index) =>
                (
                    <div className="card collection-card" key={index}>
                        <p><strong>ID: </strong>{collection.id}</p>
                        <p><strong>User ID: </strong>{collection.userId}</p>
                        <p><strong>Visibility: </strong>{collection.visibility ? "Public" : "Private"}</p>
                        <p><strong>Created At: </strong>{collection.createdAt}</p>

                        <strong>Files:</strong>
                        <FileListComponent collection={collection} collectionService={collectionService}/>
                    </div>
                ))
            }
            </div>
        </div>
    )
}

export default CollectionsView;