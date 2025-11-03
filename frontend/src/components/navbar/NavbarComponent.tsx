import React from "react";
import { Link } from "react-router-dom";
import "./NavbarComponent.css";

function NavbarComponent(): React.ReactElement
{
    return (
        <nav className="navbar-component" role="navigation" aria-label="Main navigation">
            <div className="navbar-inner">
                <div className="brand">
                    <Link to="/" className="brand-name">Fileshare</Link>
                </div>

                <div className="separator" aria-hidden="true">|</div>

                <div className="nav-links">
                    <Link to="/collections" className="nav-link">Collections</Link>
                    <Link to="/users" className="nav-link">Users</Link>
                    <Link to="/profile" className="nav-link">Profile</Link>
                </div>
            </div>
        </nav>
    )
}

export default NavbarComponent;