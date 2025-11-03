import React, { useState } from "react";
import ApiService from "../../services/ApiService";
import RegisterDto from "../../models/auth/RegisterDto";

function RegisterComponent(): React.ReactElement
{
    const [username, setUsername] = useState("");
    const [emailAddress, setEmailAddress] = useState("");
    const [password, setPassword] = useState("");
    const [invalidRegistration, setInvalidRegistration] = useState(false);

    const handleSubmit = async(e: React.FormEvent<HTMLFormElement>) =>
    {
        e.preventDefault();

        const registerDetails: RegisterDto = {username: username, emailAddress: emailAddress, password: password};

        if(await ApiService.register(registerDetails))
        {
            setInvalidRegistration(false);
        }
        else setInvalidRegistration(true);
    }

    return (
        <form onSubmit={handleSubmit}>
            <label htmlFor="username">Username:</label>
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} /><br />

            <label htmlFor="emailAddress">Email Address:</label>
            <input type="text" value={emailAddress} onChange={(e) => setEmailAddress(e.target.value)} /><br />

            <label htmlFor="password">Password:</label>
            <input type="text" value={password} onChange={(e) => setPassword(e.target.value)} /><br />

            <button type="submit">Submit</button>

            {invalidRegistration ? <span>Invalid registration!</span> : <></>}
        </form>
    )
}

export default RegisterComponent;