import React from "react"
import LoginComponent from "../../components/login/LoginComponent";
import RegisterComponent from "../../components/register/RegisterComponent";

function HomeView(): React.ReactElement
{
    return (
        <div>
            <h1>Welcome to Fileshare!</h1>
            <h4>By Seth Freeman, Dennis Araiza, and Marc Reyes</h4>
            <br/>

            <strong>Login:</strong>
            <LoginComponent />
            <br/>

            <strong>Register:</strong>
            <RegisterComponent />
        </div>
    );
}

export default HomeView;