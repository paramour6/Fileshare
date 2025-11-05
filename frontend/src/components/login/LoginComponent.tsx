import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ApiService from "../../services/ApiService";
import LoginDto from "../../auth/LoginDto";
import { useAuth } from "../../auth/AuthContext";

function LoginComponent(): React.ReactElement
{
    const {login} = useAuth();
    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [invalidCredentials, setInvalidCredentials] = useState(false);

    const handleSubmit = async(e: React.FormEvent<HTMLFormElement>) =>
    {
        e.preventDefault();
        
        const loginDetails: LoginDto = {username: username, password: password};

        if(!(await ApiService.login(loginDetails, login)))
        {
            setInvalidCredentials(true);
        }
        else navigate("/");
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