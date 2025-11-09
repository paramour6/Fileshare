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

    public async getUserById(id: number): Promise<UserDto | undefined>
    {
        try
        {
            const response = await this.backend.get("/users/" + id);

            return response.data;
        }
        catch(error)
        {
            console.error("Error getting user by ID!");

            return undefined;
        }
    }

    public async getUserByUsername(username: string): Promise<UserDto | undefined>
    {
        try
        {
            const response = await this.backend.get("/users?username=" + username);

            return response.data;
        }
        catch(error)
        {
            console.error("[UserService] Error getting user by username!");

            return undefined;
        }
    }

    public async updateUser(user: UserDto): Promise<boolean>
    {
        try
        {
            const response = await this.backend.post("/users/" + user.id, user);
            
            if(response.status !== 200) throw new Error("Error updating user!");

            return true;
        }
        catch(error)
        {
            console.error(error);
            return false;
        }
    }
}