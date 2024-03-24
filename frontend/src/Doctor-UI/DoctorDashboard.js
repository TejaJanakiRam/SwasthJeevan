import { useState, useEffect } from 'react'
import axios from "axios";
import Navbar from '../Common-UI/Navbar/Navbar.js';
import AppointmentList from './AppointmentList/AppointmentList.js';
export default function DoctorDashboard(props) {
    const [username, setUsername] = useState('');

    const getDetails = async () => {
        try {
            const response = await axios.get('http://localhost:4000/api/doctor/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            setUsername(response.data.username)
        } catch (error) {
            console.error("Failed to fetch user details:", error);
        }
    };
    useEffect(() => {
        getDetails();
    }, []);


    return (<div>
        <Navbar role={"doctor"} onLogout={props.onLogout} />
        <AppointmentList />

    </div>)
}