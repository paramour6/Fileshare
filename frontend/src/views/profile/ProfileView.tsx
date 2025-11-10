import React, {useEffect, useState} from "react";
import "./ProfileView.css";
import UserDto from "../../models/UserDto";
import UserService from "../../services/UserService";
import ApiService from "../../services/ApiService";
import { useAuth } from "../../auth/AuthContext";
import { useNavigate } from "react-router-dom";
import CollectionDto from "../../models/CollectionDto";
import CollectionService from "../../services/CollectionService";
import FileListComponent from "../../components/file-list/FileListComponent";

const userService: UserService = new UserService();
const collectionService: CollectionService = new CollectionService();

function ProfileView(): React.ReactElement
{
    console.log("[ProfileView] Rendering profile view component.");

    const [collectionList, setCollectionList] = useState<CollectionDto[]>([]);
    const [user, setUser] = useState<UserDto | undefined>(undefined);
    const [usernameField, setUsernameField] = useState("");
    const [emailField, setEmailField] = useState("");
    const [passwordField, setPasswordField] = useState("");
    const [isUpdating, setIsUpdating] = useState<boolean>(false);
    const {logout} = useAuth();
    const navigate = useNavigate();
    
    useEffect(() =>
        {

        const loadUser = async() =>
        {
            console.log("[ProfileView] Loading user.");

            let userId: number = ApiService.getCurrentUserId();
            let fetchedUser = await userService.getUserById(userId);

            if(fetchedUser === undefined)
            {
                logout();
                navigate("/login");
                return;
            }
            setUser(fetchedUser);
            setUsernameField(fetchedUser.username);
            setEmailField(fetchedUser.emailAddress);
        }

        const loadUserCollections = async() =>
        {
            console.log("[ProfileView] Loading user collections.");

            setCollectionList(await collectionService.getAllCollectionsByUser(ApiService.getCurrentUserId()));
        }

        loadUser();
        loadUserCollections();
    }, [logout, navigate]);

    const handleEditButton = (e: React.MouseEvent<HTMLButtonElement>) =>
    {
        e.preventDefault();

        if(!isUpdating)
        {
            e.currentTarget.disabled = true;
            setIsUpdating(true);
        }
    }

    const handleSubmit = async(e: React.FormEvent<HTMLFormElement>) =>
    {
        console.log("[ProfileView] Submitting profile update.");

        e.preventDefault();

        const updatedUsername = usernameField;
        const updatedEmail = emailField;
        const updatedPassword = passwordField;

        const userDto: UserDto = {id: user!.id, username: updatedUsername, emailAddress: updatedEmail, password: updatedPassword}

        if(await userService.updateUser(userDto))
        {
            logout();
            navigate("/login");
            return;
        }
        else
        {
            console.error("[ProfileView] Error submitting profile update!");
            
            setUsernameField("");
            setEmailField("");
            setPasswordField("");
        }
    }

    if(user === undefined) return <h1>Loading...</h1>
    
    return (
        <div className="app-container">
            <div className="card card--profile">
                <h2>Your Profile</h2>
                <form onSubmit={(e) => handleSubmit(e)}>
                    <label>ID</label>
                    <input value={user.id} disabled />

                    <label>Username</label>
                    <input disabled={!isUpdating} type="text" value={usernameField} onChange={(e) => setUsernameField(e.target.value)}/>

                    <label>Email Address</label>
                    <input disabled={!isUpdating} type="email" value={emailField} onChange={(e) => setEmailField(e.target.value)}/>

                    {isUpdating ? (
                        <>
                        <label>Password</label>
                        <input disabled={!isUpdating} type="password" value={passwordField} onChange={(e) => setPasswordField(e.target.value)}/>
                        <div style={{display: 'flex', gap: 8}}>
                            <button className="btn" type="submit">Update</button>
                        </div>
                        </>
                    ) : null}
                </form>

                <div style={{display: 'flex', gap: 8, marginTop: 12}}>
                    <button className="btn" onClick={(e) => handleEditButton(e)}>Edit Profile</button>
                    <button className="btn" onClick={() => {logout(); navigate("/login")}}>Logout</button>
                </div>
            </div>

            <h2 style={{marginTop: 18}}>Your Collections</h2>

            <div className="card-grid">
            {
                collectionList.map((collection, index) =>
                (
                    <div className="card collection-card" key={index}>
                        <p><strong>ID: </strong>{collection.id}</p>
                        <p><strong>Visibility: </strong>{collection.visibility ? "Public" : "Private"}</p>
                        <p><strong>Created At: </strong>{collection.createdAt}</p>

                        <strong>Files:</strong>
                        <FileListComponent collection={collection} collectionService={collectionService}/>
                    </div>
                ))
            }
            </div>
        </div>
    )
}

export default ProfileView;