import React from 'react';
import { AuthProvider } from './auth/AuthContext';
import NavbarComponent from './components/navbar/NavbarComponent';
import { Route, Routes } from 'react-router-dom';
import LoginComponent from './components/login/LoginComponent';
import RegisterComponent from './components/register/RegisterComponent';
import ProtectedRoute from './auth/ProtectedRoute';
import HomeView from './views/home/HomeView';
import ProfileView from './views/profile/ProfileView';
import UsersView from './views/users/UsersView';
import CollectionsView from './views/collections/CollectionsView';
import CreateCollectionComponent from './components/create-collection/CreateCollectionComponent';

function App() {
  return (
    <AuthProvider>
      <NavbarComponent />

      <Routes>
        <Route path="/login" element={<LoginComponent />} />
        <Route path="/register" element={<RegisterComponent />} />

        <Route
          path="/"
          element={
            <ProtectedRoute>
              <HomeView />
            </ProtectedRoute>
          }
        />
        <Route
          path="/profile"
          element={
            <ProtectedRoute>
              <ProfileView />
            </ProtectedRoute>
          }
        />
        <Route
          path="/users"
          element={
            <ProtectedRoute>
              <UsersView />
            </ProtectedRoute>
          }
        />
        <Route
          path="/collections"
          element={
            <ProtectedRoute>
              <CollectionsView />
            </ProtectedRoute>
          }
        />
        <Route
          path="/collections/create"
          element={
            <ProtectedRoute>
              <CreateCollectionComponent />
            </ProtectedRoute>
          }
        />
      </Routes>
    </AuthProvider>
  )
}

export default App;

// <>
//   <NavbarComponent></NavbarComponent>

//   <Routes>
//     <Route path="/" element={<HomeView />} />
//     <Route path="/collections" element={<CollectionsView />} />
//     <Route path="/users" element={<UsersView />} />
//     <Route path="/users/:username" element={<ProfileView />} />
//     <Route path="/profile" element={<ProfileView />} />
//   </Routes>
// </>