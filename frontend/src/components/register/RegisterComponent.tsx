import React, { useState } from "react";
import "./RegisterComponent.css";
import { useNavigate } from "react-router-dom";
import ApiService from "../../services/ApiService";
import RegisterDto from "../../auth/RegisterDto";

function RegisterComponent(): React.ReactElement
{
    console.log("[RegisterComponent] Rendering a register component.");

    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [emailAddress, setEmailAddress] = useState("");
    const [password, setPassword] = useState("");
    const [invalidRegistration, setInvalidRegistration] = useState(false);

    const handleSubmit = async(e: React.FormEvent<HTMLFormElement>) =>
    {
        console.log("[RegisterComponent] Handling registration submission.");

        e.preventDefault();

        if(username.length < 3 || username.length > 36)
        {
            setInvalidRegistration(true);
            return;
        }

        if(emailAddress.length < 3 || emailAddress.length > 256)
        {
            setInvalidRegistration(true);
            return;
        }

        if(password.length < 4 || password.length > 256)
        {
            setInvalidRegistration(true);
            return;
        }

        const registerDetails: RegisterDto = {username: username, emailAddress: emailAddress, password: password};

        if(!(await ApiService.register(registerDetails)))
        {
            console.error("[RegisterComponent] Invalid registration!");
            
            setInvalidRegistration(true);
        }
        else navigate("/login");
    }

    return (
        <div className="card card--form">
            <h2>Register</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="username">Username</label>
                <input name="username" type="text" value={username} onChange={(e) => setUsername(e.target.value)} />

                <label htmlFor="emailAddress">Email Address</label>
                <input name="emailAddress" type="email" value={emailAddress} onChange={(e) => setEmailAddress(e.target.value)} />

                <label htmlFor="password">Password</label>
                <input name="password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} />

                <div style={{display: 'flex', gap: 8}}>
                    <button className="btn" type="submit">Submit</button>
                </div>

                {invalidRegistration ? <span className="muted">Invalid registration!</span> : <></>}
            </form>
        </div>
    )
}

export default RegisterComponent;