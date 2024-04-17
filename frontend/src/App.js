import React, { useState, useEffect, useRef } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import AuthenticationPage from "./Login-UI/AuthenticationPage.js";
import PatientDashboard from './Patient-UI/PatientDashboard.js';
import Booking from "./Patient-UI/Booking.js";
import Appointments from "./Patient-UI/Appointments.js";
import DoctorDashboard from "./Doctor-UI/DoctorDashboard.js";
import PatientList from "./Doctor-UI/PatientList.js";
import OrgAdminDashboard from "./OrgAdmin-UI/OrgAdminDashboard.js";
import DoctorsList from "./OrgAdmin-UI/DoctorsList.js";
import SystemAdminDashboard from "./SystemAdmin-UI/SystemAdminDashboard.js";
import VideoRoom from "./Common-UI/VideoRoom/VideoRoom.js";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(0);
  const [token, setToken] = useState('');

  const runEffect = useRef(false);
  useEffect(() => {
    console.log("mounted");
    if (runEffect.current === false) {
      const data = JSON.parse(window.localStorage.getItem('loggedInState'));
      if (data) {
        setIsLoggedIn(data.isLoggedIn);
        setToken(data.token)
      }
    }
    return (() => { runEffect.current = true; })
  }, []);

  useEffect(() => {
    window.localStorage.setItem('loggedInState', JSON.stringify({ "token": token, "isLoggedIn": isLoggedIn }));
  }, [isLoggedIn]);

  

  const handleLogin = (jwtToken, role) => {
    setToken(jwtToken);
    if (role === 'patient') { setIsLoggedIn(1) }
    else if (role === 'doctor') { setIsLoggedIn(2) }
    else if(role === 'org_admin'){setIsLoggedIn(3)}
    else if(role === 'sys_admin'){setIsLoggedIn(4)}
  };

  const handleLogout = () => {
    setIsLoggedIn(0);
    setToken('');
  };

  const roleDashboardPaths = {
    1: '/patient/dashboard',
    2: '/doctor/dashboard',
    3: '/org_admin/dashboard',
    4: '/sys_admin/dashboard'
  };
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={isLoggedIn ? <Navigate to={roleDashboardPaths[isLoggedIn]} /> : <AuthenticationPage onLogin={handleLogin} />} />
          <Route path="/patient/dashboard" element={isLoggedIn === 1 ? <PatientDashboard token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/patient/booking" element={isLoggedIn === 1 ? <Booking token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/patient/appointments" element={isLoggedIn === 1 ? <Appointments token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/doctor/dashboard" element={isLoggedIn === 2 ? <DoctorDashboard token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/doctor/patients" element={isLoggedIn === 2 ? <PatientList token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/doctor/room/:roomId" element={isLoggedIn === 2 ? <VideoRoom /> : <Navigate to="/" />} />
          <Route path="/org_admin/dashboard" element={isLoggedIn === 3 ? <OrgAdminDashboard token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/org_admin/manage_doctors" element={isLoggedIn === 3 ? <DoctorsList token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/sys_admin/dashboard" element={isLoggedIn === 4 ? <SystemAdminDashboard token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
        </Routes>
      </BrowserRouter>


    </div>
  );
}

export default App;