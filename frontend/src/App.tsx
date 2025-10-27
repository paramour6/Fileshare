import React from 'react';
import {BrowserRouter, Routes, Route, useParams} from "react-router-dom";
import Login from "./views/LoginView";
import Home from "./views/HomeView";
import Register from "./views/RegisterView"
import Files from "./views/FilesView"

function FilesRouteWrapper(): React.ReactElement {
  const params = useParams();
  const username = params.username ?? "";
  return <Files username={username} />;
}

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}></Route>
        <Route path="/login" element={<Login/>}></Route>
        <Route path="/register" element={<Register/>}></Route>
        <Route path="/files/:username" element={<FilesRouteWrapper/>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;