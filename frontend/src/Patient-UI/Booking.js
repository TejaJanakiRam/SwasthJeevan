import ConsultForm from "./Booking/ConsultForm.js"
import Navbar from "../Common-UI/Navbar/Navbar.js"
import { useState, useEffect } from "react"
import axios from "axios";

export default function Booking(props) {
    const [currentTab, setCurrentTab] = useState('book')
    const [user, setUser] = useState(null);
    const getDetails = async () => {
        try {
            const response = await axios.get('http://localhost:4000/api/patient/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}`
                }
            });
            setUser((response.data))
        } catch (error) {
            console.error("Failed to fetch user details:", error);
        }
    };

    useEffect(() => {
        getDetails();
    }, []);

    if (user == null) {
        return null;
    }
    return (

        <div className="min-h-screen bg-gradient-to-r from-blue-100  to-blue-500 my-auto flex flex-col">
            <Navbar role={"patient"} onLogout={props.onLogout} />

            <div className="flex-grow flex justify-center items-center ">
                <div className="tab-section  bg-white  w-[600px] lg:w-[1000px] h-[600px] rounded-2xl shadow-lg shadow-slate-800">
                    <div className="tab-buttons text-lg border border-blue-600 bg-blue-500 inline-block rounded-tl-2xl  ">
                        {/* <button className={`py-3 px-4 text-sm transition duration-300 shadow shadow-slate-800 ${currentTab === 'book' ? "text-white bg-blue-500 shadow-inner shadow-slate-600" : "text-white bg-blue-500"} rounded-tl-xl`} onClick={() => { setCurrentTab('book') }}>Book Appointment</button> */}
                        <button className={`py-3 px-4 text-sm transition duration-300 shadow shadow-slate-800 ${currentTab === 'consult' ? "text-white bg-blue-500 shadow-inner shadow-slate-600" : "text-white bg-blue-500"} rounded-tl-xl`} onClick={() => { setCurrentTab('consult') }}>Consult Now</button>
                    </div>
                    <div className="tab-content h-full">
                        <ConsultForm user={user} token={props.token} />
                    </div>
                </div>
            </div>

        </div>
    )
}