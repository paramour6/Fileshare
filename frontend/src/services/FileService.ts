import FileDto from "../models/FileDto";

export const getFiles = async (username: string): Promise<void> =>
{
    const response = await fetch(process.env.REACT_APP_BACKEND_URL + "/files/" + username);

    if(!response.ok)
    {
        throw new Error("Login failed!");
    }

    return response.json();
}