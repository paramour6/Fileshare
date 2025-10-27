import React from "react";
import { FormEvent } from "react";
import {useState} from "react";
import { useNavigate } from "react-router-dom";
import "./LoginView.css";
import LoginDto from "../models/LoginDto"
import {Login} from "../services/AuthenticationService";

function LoginView(): React.ReactElement
{
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) =>
    {
        event.preventDefault();

        const formData = new FormData(event.currentTarget);
        const username = formData.get("username") as string;
        const password = formData.get("password") as string;

        const loginData: LoginDto = {
            username: username,
            password: password
        }

        try
        {
            await Login(loginData);
            // On successful login redirect to the user's files page
            navigate(`/files/${username}`);
        }
        catch(error)
        {
            console.error("Login failed: ", error);
        }
    }

    return (
        <div className="login-view">
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="username">Username:</label>
                <input
                    type="text"
                    name="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />

                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button type="submit">Login</button>
            </form>
        </div>
    );
}

export default LoginView;