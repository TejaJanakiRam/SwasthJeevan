import axios from "axios";
import Navbar from "../Common-UI/Navbar/Navbar.js"
import { useEffect, useState } from "react";

export default function Profile(props) {
    const [patientProfile, setpatientProfile] = useState(null);

    const getDetails = async () => {
        try {
            const response = await axios.get('http://localhost:4000/api/patient/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            setpatientProfile(response.data);
        } catch (error) {
            console.error("Failed to fetch user details:", error);
        }
    };

    useEffect(() => {
        getDetails();
    }, []);

    return (
        <div>
            <Navbar role={"patient"} onLogout={props.onLogout} />
            {patientProfile && (
                <div className="min-h-screen flex justify-center items-center bg-gradient-to-r from-blue-100 to-blue-500">
                    <div className="bg-white p-10 rounded-lg shadow-md max-w">
                        <div className="font-bold text-7xl mb-10 text-center">Welcome {patientProfile.username}</div>
                        <div className="grid grid-cols-2 gap-y-4 gap-x-4 text-2xl ">
                            <div className="font-semibold">SYSTEM ID:</div>
                            <div>{patientProfile.id}</div>
                            <div className="font-semibold">Name:</div>
                            <div>{patientProfile.name}</div>
                            <div className="font-semibold">Email:</div>
                            <div>{patientProfile.email}</div>
                            <div className="font-semibold">Phone:</div>
                            <div>{patientProfile.phone}</div>
                            <div className="font-semibold">Gender:</div>
                            <div>{patientProfile.gender}</div>
                            <div className="font-semibold">DOB:</div>
                            <div>{patientProfile.dob}</div>
                            <div className="font-semibold">Address:</div>
                            <div>{patientProfile.address}</div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}
