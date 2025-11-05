import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import ApiService from "../../services/ApiService";
import RegisterDto from "../../auth/RegisterDto";

function RegisterComponent(): React.ReactElement
{
    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [emailAddress, setEmailAddress] = useState("");
    const [password, setPassword] = useState("");
    const [invalidRegistration, setInvalidRegistration] = useState(false);

    const handleSubmit = async(e: React.FormEvent<HTMLFormElement>) =>
    {
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
            setInvalidRegistration(true);
        }
        else navigate("/login");
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