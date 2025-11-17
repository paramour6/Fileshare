import React from "react"

function HomeView(): React.ReactElement
{
    console.log("[HomeView] Rendering home view component!");
    
    return (
        <div>
            <img src="favicon512.png" width={412} height={412} alt="Fileshare Logo" />
            <h1>Welcome to Fileshare!</h1>
            <h4>By Seth Freeman, Dennis Araiza, and Marc Reyes</h4>
        </div>
    );
}

export default HomeView;    