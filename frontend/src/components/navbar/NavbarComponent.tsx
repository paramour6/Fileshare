import React from "react";
import { Link } from "react-router-dom";
import "./NavbarComponent.css";
import { useAuth } from "../../auth/AuthContext";

function NavbarComponent(): React.ReactElement
{
    console.log("[NavbarComponent] Rendering a navbar component.");
    
    const {isAuthenticated} = useAuth();

    const unauthenticatedNavbar = (): React.ReactElement =>
    {
        return (
            <nav className="navbar-component" role="navigation" aria-label="Main navigation">
            <div className="navbar-inner">
                <div className="brand">
                    <Link to="/" className="brand-name">Fileshare</Link>
                </div>

                <div className="separator" aria-hidden="true">|</div>

                <div className="nav-links">
                    <Link to="/login" className="nav-link">Login</Link>
                    <Link to="/register" className="nav-link">Register</Link>
                </div>
            </div>
        </nav>
        )
    }

    const authenticatedNavbar = (): React.ReactElement =>
    {
        return (
        <nav className="navbar-component" role="navigation" aria-label="Main navigation">
            <div className="navbar-inner">
                <div className="brand">
                    <Link to="/" className="brand-name">Fileshare</Link>
                </div>

                <div className="separator" aria-hidden="true">|</div>

                <div className="nav-links">
                    <Link to="/users" className="nav-link">Users</Link>
                    <Link to="/collections" className="nav-link">Collections</Link>
                    <Link to="/profile" className="nav-link">Profile</Link>
                </div>
            </div>
        </nav>
        )
    }

    return isAuthenticated ? authenticatedNavbar() : unauthenticatedNavbar();
}

export default NavbarComponent;