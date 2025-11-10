import React from "react";
import { useAuth } from "./AuthContext";
import { Navigate } from "react-router-dom";

function ProtectedRoute({children}: {children: React.ReactElement}): React.JSX.Element
{
    console.log("[ProtectedRoute] Rendering a protected route component.");
    
    const {isAuthenticated} = useAuth();

    if(isAuthenticated)
    {
        return children;
    }

    return <Navigate to="/login" replace />
}

export default ProtectedRoute;