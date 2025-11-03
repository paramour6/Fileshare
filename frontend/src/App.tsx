import React from 'react';
import {Routes, Route} from "react-router-dom";
import NavbarComponent from './components/navbar/NavbarComponent';
import HomeView from './views/home/HomeView';
import CollectionsView from './views/collections/CollectionsView';
import UsersView from './views/users/UsersView';
import ProfileView from './views/profile/ProfileView';

function App() {
  return (
    <>
      <NavbarComponent></NavbarComponent>

      <Routes>
        <Route path="/" element={<HomeView />} />
        <Route path="/collections" element={<CollectionsView />} />
        <Route path="/users" element={<UsersView />} />
        <Route path="/users/:username" element={<ProfileView />} />
        <Route path="/profile" element={<ProfileView />} />
      </Routes>
    </>
  );
}

export default App;