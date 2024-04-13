import { useState, useEffect } from "react";
import axios from "axios";
import Sidebar from "../Common-UI/Sidebar/Sidebar";
import Navbar from "../Common-UI/Navbar/Navbar";

export default function SystemAdminDashboard(props) {
    const [username,setUsername] = useState('');

    return (
        <div>
          <Navbar role={"sys_admin"} onLogout={props.onLogout} />
          <div className='bg-blue-400 w-1000px h-screen'></div>
        </div>
      )
}