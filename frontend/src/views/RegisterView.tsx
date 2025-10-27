import React from "react";
import { FormEvent } from "react";
import {useState} from "react";
import "./RegisterView.css";
import RegisterDto from "../models/RegisterDto"
import {Register} from "../services/AuthenticationService";

function RegisterView(): React.ReactElement
{
    const [username, setUsername] = useState("");
    const [emailAddress, setEmailAddress] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) =>
    {
        event.preventDefault();

        const formData = new FormData(event.currentTarget);
        const username = formData.get("username") as string;
        const emailAddress = formData.get("emailAddress") as string;
        const password = formData.get("password") as string;

        const registerData: RegisterDto = {
            username: username,
            emailAddress: emailAddress,
            password: password
        }

        try
        {
            const result = await Register(registerData);
            console.log(result);
        }
        catch(error)
        {
            console.error("Registration failed: ", error);
        }
    }

    return (
        <div className="register-view">
            <h2>Register</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="username">Username:</label>
                <input
                    type="text"
                    name="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />

                <label htmlFor="emailAddress">Email Address:</label>
                <input
                    type="text"
                    name="emailAddress"
                    value={emailAddress}
                    onChange={(e) => setEmailAddress(e.target.value)}
                />

                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button type="submit">Register</button>
            </form>
        </div>
    );
}

export default RegisterView;