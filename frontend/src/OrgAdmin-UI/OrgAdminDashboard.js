import { useState, useEffect } from 'react'
import axios from "axios";
import Sidebar from '../Common-UI/Sidebar/Sidebar';





export default function OrgAdminDashboard(props) {
  const [username, setUsername] = useState('');
  //   const getDetails = async () => {
  //     try {
  //       const response = await axios.get('http://localhost:4000/api/patient/profile', {
  //         headers: {
  //           Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
  //         }
  //       });
  //       setUsername(response.data.username)
  //     } catch (error) {
  //       console.error("Failed to fetch user details:", error);
  //     }
  //   };
  //   useEffect(() => {
  //     getDetails();
  //   }, []);

  return (
    <div>
      <Sidebar role={"org_admin"} onLogout={props.onLogout} />
      <div className='bg-blue-400 h-[2000px]'></div>
    </div>
  )



}