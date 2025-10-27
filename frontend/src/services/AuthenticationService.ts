import LoginDto from "../models/LoginDto";
import RegisterDto from "../models/RegisterDto";

export const Login = async (loginData: LoginDto): Promise<void> =>
{
    const request = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(loginData)
    };

    const response = await fetch(process.env.REACT_APP_BACKEND_URL + "/login", request);

    if(!response.ok)
    {
        throw new Error("Login failed!");
    }

    return response.json();
}

export const Register = async(registerData: RegisterDto): Promise<void> =>
{
    const request = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(registerData)
    }

    const response = await fetch(process.env.REACT_APP_BACKEND_URL + "/register", request);

    if(!response.ok)
    {
        throw new Error("Registration failed!");
    }

    return response.json();
}