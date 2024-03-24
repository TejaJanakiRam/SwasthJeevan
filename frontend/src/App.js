import React, { useState } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import AuthenticationPage from "./Login-UI/AuthenticationPage.js";
import PatientDashboard from './Patient-UI/PatientDashboard.js';
import Booking from "./Patient-UI/Booking.js";
import DoctorDashboard from "./Doctor-UI/DoctorDashboard.js";
import PatientList from "./Doctor-UI/PatientList.js";
function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(0);
  const [token, setToken] = useState('');

  const handleLogin = (jwtToken, role) => {
    setToken(jwtToken);
    if (role === 'patient') { setIsLoggedIn(1) }
    else if (role === 'doctor') { setIsLoggedIn(2) }
  };

  const handleLogout = () => {
    setIsLoggedIn(0);
  };

  const roleDashboardPaths = {
    1: '/patient/dashboard',
    2: '/doctor/dashboard',
  };
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={isLoggedIn ? <Navigate to={roleDashboardPaths[isLoggedIn]}/> : <AuthenticationPage onLogin={handleLogin} />} />
          <Route path="/patient/dashboard" element={isLoggedIn === 1 ? <PatientDashboard token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/patient/booking" element={isLoggedIn === 1 ? <Booking token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/doctor/dashboard" element={isLoggedIn === 2 ? <DoctorDashboard token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/doctor/patients" element={isLoggedIn === 2 ? <PatientList token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
        </Routes>
      </BrowserRouter>


    </div>
  );
}

export default App;
