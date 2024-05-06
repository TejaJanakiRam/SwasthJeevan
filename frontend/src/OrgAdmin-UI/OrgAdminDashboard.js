import { useState, useEffect } from 'react'
import axios from "axios";
import Sidebar from '../Common-UI/Sidebar/Sidebar';

export default function OrgAdminDashboard(props) {
  const [username, setUsername] = useState('');
 
  return (
    <div>
      <Sidebar role={"org_admin"} onLogout={props.onLogout} />
    </div>
  )

}