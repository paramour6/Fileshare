import React, { createContext, useContext, useState } from "react";

interface IAuthContext
{
    token: string | null;
    isAuthenticated: boolean;
    login: (token: string, userId: number) => void;
    logout: () => void;
}

const AuthContext = createContext<IAuthContext | undefined>(undefined);

export const AuthProvider = ({children}: {children: React.ReactNode}) =>
{
    const [token, setToken] = useState<string | null>(null);
    
    const isAuthenticated: boolean = (token !== null && token.length > 0);

    const login = (jwt: string, userId: number) =>
    {
        console.log("[AuthContext] Saving login details.");

        localStorage.setItem("token", jwt);
        localStorage.setItem("user_id", userId.toString());
        setToken(jwt);
    }

    const logout = () =>
    {
        console.log("[AuthContext] Logging out.");
        
        localStorage.removeItem("token");
        localStorage.removeItem("user_id");
        setToken(null);
    }


    return (
        <AuthContext.Provider value={{token, isAuthenticated, login, logout}}>
            {children}
        </AuthContext.Provider>
    )
}

export const useAuth = (): IAuthContext =>
{
    const context = useContext(AuthContext);

    if(context === undefined)
    {
        throw new Error("useAuth does not have an AuthProvider!");
    }

    return context;
}