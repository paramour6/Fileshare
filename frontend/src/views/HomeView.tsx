import React from "react"
import { Link } from "react-router-dom";
import "./HomeView.css";

function HomeView(): React.ReactElement
{
    return (
        <div className="home-view">
            <ul>
                <li>
                    <Link to="/login">Login</Link>
                </li>
                <li>
                    <Link to="/register">Register</Link>
                </li>
            </ul>
        </div>
    );
}

export default HomeView;