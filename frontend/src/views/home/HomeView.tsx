import React from "react"

function HomeView(): React.ReactElement
{
    return (
        <div>
            <img src="favicon512.png" width={360} height={360} alt="Fileshare Logo" />
            <h1>Welcome to Fileshare!</h1>
            <h4>By Seth Freeman, Dennis Araiza, and Marc Reyes</h4>
        </div>
    );
}

export default HomeView;    