import axios, { AxiosInstance } from "axios";
import LoginDto from "../auth/LoginDto";
import RegisterDto from "../auth/RegisterDto";

export default class ApiService
{
    private static API_URL: string = process.env.REACT_APP_BACKEND_URL!;

    public static getInstance(): AxiosInstance
    {
        console.log("[ApiService] Creating Axios API instance.");
        
        const backend = axios.create(
            {
                baseURL: ApiService.API_URL
            }
        );

        backend.interceptors.request.use(config =>
        {
            const jwt = localStorage.getItem("token");
            const header = (jwt !== null && jwt.length > 0) ? `Bearer ${jwt}` : "";
            config.headers.Authorization = header;

            return config;
        });

        return backend;
    }

    public static async login(loginDetails: LoginDto, callback: (token: string, userId: number) => void): Promise<boolean>
    {
        console.log("[ApiService] Logging user in.");

        try
        {
            const response = await axios.post(ApiService.API_URL + "/login", loginDetails);

            if(response.status !== 200) return false;

            let token: string = response.data;
            let userId: number | undefined = await ApiService.fetchCurrentUserId(token)

            if(userId === undefined) return false;

            callback(token, userId);

            console.log("[ApiService] Login successful. Redirecting.");
            return true;
        }
        catch(error)
        {
            console.error("[ApiService] Invalid login!");

            return false;
        }
    }

    public static async register(registerDetails: RegisterDto): Promise<boolean>
    {
        console.log("[ApiService] Registering user.");

        try
        {
            const response = await axios.post(ApiService.API_URL + "/register", registerDetails);

            return response.status === 200;
        }
        catch(err)
        {
            console.error("[ApiService] Invalid registration!");

            return false;
        }
    }

    public static getCurrentUserId(): number
    {
        console.log("[ApiService] Getting current user ID from local storage.");

        let userId: string | null = localStorage.getItem("user_id");

        if(userId !== null)
        {
            let id = parseInt(userId);

            if(isNaN(id)) throw new Error("[ApiService] Could not parse user ID!");

            return id;
        }
        else throw new Error("[ApiService] Could not get user_id from local storage!");
    }

    private static async fetchCurrentUserId(token: string): Promise<number | undefined>
    {
        console.log("[ApiService] Fetching current user ID.");

        try
        {
            const response = await axios.get(ApiService.API_URL + "/curuserid", {headers: {Authorization: `Bearer ${token}`}});

            return response.data;
        }
        catch(error)
        {
            console.error("[ApiService] Could not fetch current user ID!");

            return undefined;
        }
    }
}