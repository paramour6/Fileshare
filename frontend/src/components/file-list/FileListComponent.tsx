import React, { useEffect, useState } from "react";
import "./FileListComponent.css";
import CollectionDto from "../../models/CollectionDto";
import FileDto from "../../models/FileDto";
import CollectionService from "../../services/CollectionService";

interface FileListProps
{
    collection: CollectionDto,
    collectionService: CollectionService
}

const FileListComponent: React.FC<FileListProps> = (props: FileListProps) =>
{
    const collection: CollectionDto = props.collection;
    const collectionService: CollectionService = props.collectionService;
    const [fileList, setFileList] = useState<FileDto[]>([]);
    const [urls, setUrls] = useState<Record<string, string | null>>({});

    useEffect(() =>
    {
        const getFiles = async() =>
        {
            const urls: Record<string, string | null> = {};
            const files = await collectionService.getCollectionFiles(collection);
            
            setFileList(files);

            for(const file of files)
            {
                urls[file.fileName] = await collectionService.downloadFile(file);
            }

            setUrls(urls);
        }

        getFiles();
    }, [collection, collectionService]);

    const getDownloadAnchor = (filename: string) =>
    {
        if((urls[filename] === undefined) || (urls[filename] === null))
        {
            return (
                <span className="muted">Loading...</span>
            )
        }

        return (
            <a className="" href={urls[filename]!} download={filename}>Download</a>
        )
    }

    return (
        <div>
            {fileList.length === 0 ? (
                <div className="file-empty">No files found.</div>
            ) : (
                <div className="file-list">
                    {
                        fileList.map((file) => (
                            <div className="file-row" key={file.fileName}>
                                <div className="file-meta">
                                    <div className="file-name">{file.fileName}</div>
                                    <div className="file-size">{file.fileSizeKb} KB</div>
                                </div>

                                <div className="file-action">
                                    {getDownloadAnchor(file.fileName)}
                                </div>
                            </div>
                        ))
                    }
                </div>
            )}
        </div>
    )
}

export default FileListComponent;