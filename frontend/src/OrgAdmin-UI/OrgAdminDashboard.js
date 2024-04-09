import { useState, useEffect } from 'react'
import axios from "axios";





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
      Hello
    </div>
  )



}