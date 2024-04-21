import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from '../Common-UI/Navbar/Navbar.js';
import AppointmentList from './AppointmentList/AppointmentList.js';

const DoctorDashboard = (props) => {
  const [doctorProfile, setDoctorProfile] = useState(null);

  const getDetails = async () => {
    try {
      const response = await axios.get('http://localhost:4000/api/doctor/profile', {
        headers: {
          Authorization: `Bearer ${props.token}`
        }
      });
      setDoctorProfile(response.data);
    } catch (error) {
      console.error('Failed to fetch user details:', error);
    }
  };

  useEffect(() => {
    getDetails();
  }, []);

  return (
    <div >
      <Navbar role="doctor" onLogout={props.onLogout} />
      {doctorProfile && doctorProfile.id && <AppointmentList doctorProfile={doctorProfile} token={props.token} />}
    </div>
  );
};

export default DoctorDashboard;
