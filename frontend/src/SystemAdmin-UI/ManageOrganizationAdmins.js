import Sidebar from "../Common-UI/Sidebar/Sidebar";
import { FaPlus } from "react-icons/fa";
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import Loading from "../Common-UI/Loading/Loading";
import axios from "axios";
import OrganizationAdminsList from "./OrganizationAdminsList";


export default function ManageOrganizationAdmins(props) {
    const [organizationsList, setOrganizationsList] = useState(null);
    const [organizationAdminsList, setOrganizationAdminsList] = useState(null);
    const getAllOrganizations = async () => {
        try {
            const response = await axios.get('http://localhost:4000/api/organizations', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            setOrganizationsList(response.data)

        } catch (error) {
            console.error("Failed to fetch organizations:", error);
        }
    }
    const getAllOrganizationAdmins = async () => {
        try {
            const response = await axios.get('http://localhost:4000/api/org_admin/all', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            setOrganizationAdminsList(response.data)

        } catch (error) {
            console.error("Failed to fetch organization admins:", error);
        }
    }

    useEffect(() => {
        getAllOrganizations();
        getAllOrganizationAdmins();
    }, []);
    return (<div><Sidebar role={"sys_admin"} onLogout={props.onLogout} />
        <div className="bg-blue-400 h-screen flex justify-center items-center">
            <div className=" w-4/6 h-5/6 bg-white rounded-2xl shadow-lg shadow-slate-800 p-8">
                <div className="flex justify-end mb-4">
                    <Link to="/sys_admin/manage_organization_admins/add">
                        <div className=" flex items-center bg-blue-500 p-4 text-white text-xl rounded-2xl hover:bg-blue-600 transition-all duration-300">
                            <span className="text-white  hover:cursor-pointer pr-2"><FaPlus /></span> Add Organization Admin
                        </div>
                    </Link>
                </div>
                {organizationAdminsList && organizationsList ? organizationAdminsList.length > 0 ? <OrganizationAdminsList data={organizationAdminsList} organizationsList={organizationsList}/> : <div>No Organization Admins Available</div> : <Loading />}


            </div>
        </div>
    </div>)
}