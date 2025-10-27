import React from "react";
import "./FilesView.css";
import FileDto from "../models/FileDto";

function FilesView({ username }: { username: string }): React.ReactElement {
    const files: FileDto[] = [
        { fileName: "test", fileType: "txt", fileSizeKb: 23 },
        { fileName: "vid", fileType: "mp4", fileSizeKb: 24000 },
        { fileName: "myPdf", fileType: "pdf", fileSizeKb: 13529 },
        { fileName: "schedule", fileType: "txt", fileSizeKb: 10 },
        { fileName: "prog", fileType: "exe", fileSizeKb: 234828 },
    ];

    const formatSize = (kb: number): string => {
        if (kb >= 1024 * 1024) {
            return `${(kb / (1024 * 1024)).toFixed(2)} GB`;
        }
        if (kb >= 1024) {
            return `${(kb / 1024).toFixed(2)} MB`;
        }
        return `${kb} KB`;
    };

    return (
        <div className="files-view">
            <h2>Files by {username}</h2>
            <table className="files-table">
                <thead>
                    <tr>
                        <th>File name</th>
                        <th>Type</th>
                        <th>Size</th>
                        <th>Download</th>
                    </tr>
                </thead>
                <tbody>
                    {files.map((f) => (
                        <tr key={f.fileName}>
                            <td>{f.fileName}</td>
                            <td>{f.fileType}</td>
                            <td>{formatSize(f.fileSizeKb)}</td>
                            <td>Download (not available, not stored on cloud)</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default FilesView;