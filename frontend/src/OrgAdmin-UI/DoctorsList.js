import { useEffect, useState } from "react";
import Sidebar from "../Common-UI/Sidebar/Sidebar";
import Loading from "../Common-UI/Loading/Loading";
import { FaPlus } from "react-icons/fa";
import { Link } from "react-router-dom";
import axios from "axios";

export default function DoctorsList(props) {

    const [doctorsList, setDoctorsList] = useState(null);
    const getAllDoctors = async () => {
        try {
            const response = await axios.get('http://localhost:4000/api/doctor/all', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            setDoctorsList((response.data));
        } catch (error) {
            console.error("Failed to fetch doctors:", error);
        }
    }
    useEffect(() => {
        getAllDoctors();
    }, []);
    return (
        <div>
            <Sidebar role={"org_admin"} onLogout={props.onLogout} />
            <div className="bg-blue-400 h-screen flex justify-center items-center">
                <div className=" w-4/6 h-5/6 bg-white rounded-2xl shadow-lg shadow-slate-800">
                    {doctorsList ? <ul className="flex flex-col justify-center">
                        {doctorsList.map((item) => {
                            return (<li className="bg-blue-400 mx-16 p-4 my-4 rounded-xl text-white" key={item.id}>{item.name}</li>)
                        })}
                    </ul> : <Loading />}
                    <div className="flex justify-end mx-16">
                        <Link to="/org_admin/manage_doctors/add">
                            <div className=" flex items-center bg-blue-500 p-4 text-white text-xl rounded-2xl hover:bg-blue-600 transition-all duration-300">
                                <span className="text-white  hover:text-blue-500 hover:cursor-pointer pr-2"><FaPlus /></span> Add Doctor
                            </div>
                        </Link>
                    </div>


                </div>
            </div>
        </div >)
}