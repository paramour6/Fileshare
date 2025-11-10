import React, { useEffect, useState } from "react";
import "./UsersView.css";
import UserService from "../../services/UserService";
import UserDto from "../../models/UserDto";

function UsersView(): React.ReactElement
{
    console.log("[UsersView] Rendering users view component.");

    const [userList, setUserList] = useState<UserDto[]>([]);

    useEffect(() =>
    {
        const userService: UserService = new UserService();
        const getUsers = async() =>
        {
            console.log("[UsersView] Loading user list.");
            
            setUserList(await userService.getAllUsers());
        }

        getUsers();
    }, []);

    return (
        <div className="app-container">
            <h1>Users</h1>
            <div className="card-grid">
            {
                userList.map((user, index) =>
                (
                    <div className="card user-card" key={index}>
                        <p><strong>ID: </strong>{user.id}</p>
                        <p><strong>Username: </strong>{user.username}</p>
                        <p><strong>Email Address: </strong>{user.emailAddress}</p>
                    </div>
                ))
            }
            </div>
        </div>
    )
}

export default UsersView;