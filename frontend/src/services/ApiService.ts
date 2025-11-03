import axios, { AxiosInstance } from "axios";
import LoginDto from "../models/auth/LoginDto";
import RegisterDto from "../models/auth/RegisterDto";

export default class ApiService
{
    private static API_URL: string = process.env.REACT_APP_BACKEND_URL!;
    public static getInstance(): AxiosInstance
    {
        return axios.create(
            {
                baseURL: this.API_URL,
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("jwt")
                }
            }
        )
    }

    public static async login(loginDetails: LoginDto): Promise<boolean>
    {
        try
        {
            localStorage.removeItem("jwt");
            const response = await axios.post(ApiService.API_URL + "/login", loginDetails);

            if(response.status !== 200)
            {
                throw new Error();
            }

            localStorage.setItem("jwt", response.data);
            return true;
        }
        catch(error)
        {
            console.error("[ApiService] Error logging in");
            return false;
        }
    }

    public static async register(registerDetails: RegisterDto): Promise<boolean>
    {
        try
        {
            localStorage.removeItem("jwt");
            const response = await axios.post(ApiService.API_URL + "/register", registerDetails);

            if((response.status !== 200))
            {
                throw new Error();
            }
            
            return true;
        }
        catch(error)
        {
            console.error("[ApiService] Error registering user");

            return false;
        }
    }

    public static async getCurrentUserId(): Promise<number>
    {
        try
        {
            const response = await axios.get(ApiService.API_URL + "/curuserid");

            if(response.status !== 200)
            {
                throw new Error();
            }

            return response.data;
        }
        catch(error)
        {
            console.error("[UserService] Error getting current user ID!");

            return -1;
        }
    }
}