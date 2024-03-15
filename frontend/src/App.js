import React, { useState } from "react";
import { BrowserRouter , Routes, Route, Navigate } from "react-router-dom";

import Dashboard from './Patient_UI/Dashboard.js';
import AuthenticationPage from "./components/AuthenticationPage.js";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(0);
  const [token, setToken] = useState('');

  const handleLogin = (jwtToken,role) => {
    setToken(jwtToken);
    // console.log(role);
    if(role==='PATIENT') {setIsLoggedIn(1)}
    if(role==='DOCTOR') {setIsLoggedIn(2)}
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
  };
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={isLoggedIn ? <Navigate to="/patient/dashboard" /> : <AuthenticationPage onLogin={handleLogin} />}/>
          <Route path="/patient/dashboard" element={isLoggedIn===1 ? <Dashboard token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />       
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
