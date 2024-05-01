import { useEffect, useState } from "react";
import Sidebar from "../Common-UI/Sidebar/Sidebar";
import Loading from "../Common-UI/Loading/Loading";
import { FaPlus } from "react-icons/fa";
import { Link } from "react-router-dom";
import DoctorsList from "./DoctorsList";
import axios from "axios";

export default function ManageDoctors(props) {

    const [doctorsList, setDoctorsList] = useState(null);
    const [specialitiesList, setSpecialitiesList] = useState(null);

    const getAllDoctors = async () => {
        try {
            const response = await axios.get('http://localhost:4000/api/doctor/all', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            setDoctorsList((response.data));
            console.log(response.data);
        } catch (error) {
            console.error("Failed to fetch doctors:", error);
        }
    }
    const getAllSpecialties = async () => {
        const response = await axios.get('http://localhost:4000/api/specialities', {
            headers: {
                Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
            }
        });
        setSpecialitiesList(response.data);

    }

    useEffect(() => {
        getAllSpecialties();
        getAllDoctors();
    }, []);


    return (
        <div>
            <Sidebar role={"org_admin"} onLogout={props.onLogout} />
            <div className="bg-blue-400 h-screen flex justify-center items-center">
                <div className=" w-4/6 h-5/6 bg-white rounded-2xl shadow-lg shadow-slate-800 p-16">
                    <div className="flex justify-end mb-4">
                        <Link to="/org_admin/manage_doctors/add">
                            <div className=" flex items-center bg-blue-500 p-4 text-white text-xl rounded-2xl hover:bg-blue-600 transition-all duration-300">
                                <span className="text-white  hover:cursor-pointer pr-2"><FaPlus /></span> Add Doctor
                            </div>
                        </Link>
                    </div>
                    {doctorsList && specialitiesList ? doctorsList.length > 0 ? <DoctorsList data={doctorsList} specialitiesList={specialitiesList} /> : <div>No Doctors Available</div> : <Loading />}


                </div>
            </div>
        </div>)
}