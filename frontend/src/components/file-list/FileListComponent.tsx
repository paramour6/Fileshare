// import React, { useEffect, useState } from "react";
// import CollectionDto from "../../models/CollectionDto";
// import FileDto from "../../models/FileDto";
// import CollectionService from "../../services/CollectionService";
// import CreateCollectionComponent from "../create-collection/CreateCollectionComponent";

// interface FileListProps
// {
//     collection: CollectionDto,
//     collectionService: CollectionService
// }

// const FileListComponent: React.FC<FileListProps> = (props: FileListProps) =>
// {
//     const collection: CollectionDto = props.collection;
//     const collectionService: CollectionService = props.collectionService;
//     const [fileList, setFileList] = useState<FileDto[]>([]);
//     const [urls, setUrls] = useState<Record<string, string | null>>({});

//     useEffect(() =>
//     {
//         const getFiles = async() =>
//         {
//             const urls: Record<string, string | null> = {};
//             const files = await collectionService.getCollectionFiles(collection);
            
//             setFileList(files);

//             for(const file of files)
//             {
//                 urls[file.fileName] = await collectionService.downloadFile(file);
//             }

//             setUrls(urls);
//         }

//         getFiles();
//     }, [collection, collectionService]);

//     const getDownloadAnchor = (filename: string) =>
//     {
//         if((urls[filename] === undefined) || (urls[filename] === null))
//         {
//             return (
//                 <span style={{color: "red"}}>Download Unavailable</span>
//             )
//         }

//         return (
//             <a href={urls[filename]!} download={filename}>Download</a>
//         )
//     }

//     return (
//         <div>
//             <ul>
//                 {
//                     fileList.map((file, index) =>
//                         (
//                             <ul key={index}>
//                             <li><strong>Name: </strong>{file.fileName}</li>
//                             <li><strong>File Size (KB): </strong>{file.fileSizeKb}</li>
//                             <li>{getDownloadAnchor(file.fileName)}</li>
//                         </ul>
//                     ))
//                 }
//             </ul>
            
//             <CreateCollectionComponent collectionService={collectionService} />
//         </div>
//     )
// }

const FileListComponent = () =>
{
    return (<></>)
}

export default FileListComponent;