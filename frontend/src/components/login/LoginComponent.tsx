import React, { useState } from "react";
import "./LoginComponent.css";
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
        <div className="card card--form">
            <h2>Login</h2>
            <form onSubmit={(e) => handleSubmit(e)}>
                <label htmlFor="username">Username</label>
                <input name="username" type="text" value={username} onChange={(e) => setUsername(e.target.value)} />

                <label htmlFor="password">Password</label>
                <input name="password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />

                <div style={{display: 'flex', gap: 8}}>
                    <button className="btn" type="submit">Submit</button>
                </div>

                {invalidCredentials ? (<span className="muted">Invalid credentials!</span>) : (<></>)}
            </form>
        </div>
    )
}

export default LoginComponent;