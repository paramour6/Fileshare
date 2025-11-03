import UserDto from "../models/UserDto";
import ApiService from "./ApiService";

export default class UserService
{
    private backend = ApiService.getInstance();

    public async getAllUsers(): Promise<UserDto[]>
    {
        try
        {
            const response = await this.backend.get("/users");

            if(response.status !== 200)
            {
                throw new Error();
            }

            return response.data;
        }
        catch(error)
        {
            console.error("[UserService] Error getting all users!");

            return [];
        }
    }

    // public async getUserByUsername(username: string): Promise<UserDto>
    // {
    //     try
    //     {
    //         const response = await axios.get(this.API_URL + "/users" + "?username=" + username);
    //         return response.data;
    //     }
    //     catch(error)
    //     {
    //         console.error(error);
    //         throw new Error();
    //     }
    // }

    // public async updateUser(user: UserDto): Promise<UserDto>
    // {
    //     try
    //     {
    //         const response = await axios.put(this.API_URL + "/users/" + user.id, user);
    //         return response.data;
    //     }
    //     catch(error)
    //     {
    //         console.error(error);
    //         throw new Error();
    //     }
    // }

    // public async deleteUser(id: number): Promise<void>
    // {
    //     try
    //     {
    //         await axios.delete(this.API_URL + "/users/" + id);
    //     }
    //     catch(error)
    //     {
    //         console.error(error);
    //         throw new Error();
    //     }
    // }
}