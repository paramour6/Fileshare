import React, { useEffect, useState } from "react";
import "./UsersView.css";
import UserService from "../../services/UserService";
import UserDto from "../../models/UserDto";

function UsersView(): React.ReactElement
{
    const [userList, setUserList] = useState<UserDto[]>([]);

    useEffect(() =>
    {
        const userService: UserService = new UserService();
        const getUsers = async() =>
        {
            setUserList(await userService.getAllUsers());
        }

        getUsers();
    }, []);

    return (
        <div>
            {
                userList.map((user, index) =>
                (
                    <div key={index}>
                        <p><strong>ID: </strong>{user.id}</p>
                        <p><strong>Username: </strong>{user.username}</p>
                        <p><strong>Email Address: </strong>{user.emailAddress}</p>
                    </div>
                ))
            }
        </div>
    )
}

export default UsersView;