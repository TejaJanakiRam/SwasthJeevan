import React, { useState, useEffect, useRef } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import AuthenticationPage from "./Login-UI/AuthenticationPage.js";
import PatientDashboard from './Patient-UI/PatientDashboard.js';
import Booking from "./Patient-UI/Booking.js";
import Appointments from "./Patient-UI/Appointments.js";
import DoctorDashboard from "./Doctor-UI/DoctorDashboard.js";
import PatientList from "./Doctor-UI/PatientList.js";
import OrgAdminDashboard from "./OrgAdmin-UI/OrgAdminDashboard.js";
import ManageDoctors from "./OrgAdmin-UI/ManageDoctors.js";
import DoctorSignUp from "./OrgAdmin-UI/DoctorSignUp.js";
import SystemAdminDashboard from "./SystemAdmin-UI/SystemAdminDashboard.js";
import ManageOrganizations from "./SystemAdmin-UI/ManageOrganizations.js";
import RegisterOrganization from "./SystemAdmin-UI/RegisterOrganization.js";
import OrganizationAdminSignUp from "./SystemAdmin-UI/OrganizationAdminSignUp.js";
import VideoRoom from "./Common-UI/VideoRoom/VideoRoom.js";
import Profile from "./Doctor-UI/Profile.js";
import Pprofile from "./Patient-UI/Profile.js";
import UpdateProfile from "./Patient-UI/UpdateProfile.js";
import VideoCall from "./Video/VideoCall.js";
import ManageOrganizationAdmins from "./SystemAdmin-UI/ManageOrganizationAdmins.js";
import UpdateDoctorProfile from "./Doctor-UI/UpdateDoctorProfile.js";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(0);
  const [token, setToken] = useState('');

  const runEffect = useRef(false);
  useEffect(() => {
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
    else if (role === 'org_admin') { setIsLoggedIn(3) }
    else if (role === 'sys_admin') { setIsLoggedIn(4) }
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
          <Route path="/patient/profile" element={isLoggedIn === 1 ? <Pprofile token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/patient/profile/update" element={isLoggedIn === 1 ? <UpdateProfile token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/patient/appointments" element={isLoggedIn === 1 ? <Appointments token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/doctor/dashboard" element={isLoggedIn === 2 ? <DoctorDashboard token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/doctor/patients" element={isLoggedIn === 2 ? <PatientList token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/doctor/profile" element={isLoggedIn === 2 ? <Profile token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/doctor/profile/update" element={isLoggedIn === 2 ? <UpdateDoctorProfile token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/doctor/room/:roomId" element={isLoggedIn === 2 ? <VideoRoom /> : <Navigate to="/" />} />
          <Route path="/org_admin/dashboard" element={isLoggedIn === 3 ? <OrgAdminDashboard token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/org_admin/manage_doctors" element={isLoggedIn === 3 ? <ManageDoctors token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/org_admin/manage_doctors/add" element={isLoggedIn === 3 ? <DoctorSignUp token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/sys_admin/dashboard" element={isLoggedIn === 4 ? <SystemAdminDashboard token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/sys_admin/manage_organizations" element={isLoggedIn === 4 ? <ManageOrganizations token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/sys_admin/manage_organizations/add" element={isLoggedIn === 4 ? <RegisterOrganization token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/sys_admin/manage_organization_admins" element={isLoggedIn === 4 ? <ManageOrganizationAdmins token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/sys_admin/manage_organization_admins/add" element={isLoggedIn === 4 ? <OrganizationAdminSignUp token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />
          <Route path="/videocall" element={<VideoCall />} />
        </Routes>
      </BrowserRouter>

    </div>
  );
}

export default App;