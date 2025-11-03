import React, { useState } from "react";
import ApiService from "../../services/ApiService";
import LoginDto from "../../models/auth/LoginDto";

function LoginComponent(): React.ReactElement
{
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [invalidCredentials, setInvalidCredentials] = useState(false);

    const handleSubmit = async(e: React.FormEvent<HTMLFormElement>) =>
    {
        e.preventDefault();
        
        const loginDetails: LoginDto = {username: username, password: password};

        if(await ApiService.login(loginDetails))
        {
            setInvalidCredentials(false);
        }
        else setInvalidCredentials(true);
    }

    return (
        <form onSubmit={(e) => handleSubmit(e)}>
            <label htmlFor="username">Username:</label>
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} /><br />

            <label htmlFor="password">Password:</label>
            <input type="text" value={password} onChange={(e) => setPassword(e.target.value)} /><br />

            <button type="submit">Submit</button>

            {invalidCredentials ? (<span>Invalid credentials!</span>) : (<></>)}
        </form>
    )
}

export default LoginComponent;