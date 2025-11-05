import React, { useEffect, useState } from "react";
import CollectionDto from "../../models/CollectionDto";
import CollectionService from "../../services/CollectionService";
// import FileListComponent from "../../components/file-list/FileListComponent";

function CollectionsView(): React.ReactElement
{
    const [collectionList, setCollectionList] = useState<CollectionDto[]>([]);
    const collectionService: CollectionService = new CollectionService();

    useEffect(() =>
    {
        const getCollections = async() =>
        {
            setCollectionList(await collectionService.getAllCollections());
        }

        getCollections();
    }, []);
    
    return (
        <div>
            {
                collectionList.map((collection, index) =>
                (
                    <div key={index}>
                        <p><strong>ID: </strong>{collection.id}</p>
                        <p><strong>User ID: </strong>{collection.userId}</p>
                        <p><strong>Visibility: </strong>{collection.visibility ? "Public" : "Private"}</p>
                        <p><strong>Created At: </strong>{collection.createdAt}</p>

                        <strong>Files:</strong>
                        {/* <FileListComponent collection={collection} collectionService={collectionService}/> */}
                    </div>
                ))
            }
        </div>
    )
}

export default CollectionsView;