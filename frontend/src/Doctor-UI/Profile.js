import axios from "axios";
import Navbar from "../Common-UI/Navbar/Navbar.js"
import { useEffect, useState } from "react";

export default function Profile(props) {
    const [doctorProfile, setDoctorProfile] = useState(null);

    const getDetails = async () => {
        try {
            const response = await axios.get('http://localhost:4000/api/doctor/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            setDoctorProfile(response.data);
        } catch (error) {
            console.error("Failed to fetch user details:", error);
        }
    };

    useEffect(() => {
        getDetails();
    }, []);

    return (
        <div>
            <Navbar role={"doctor"} onLogout={props.onLogout} />
            {doctorProfile && (
                <div className="min-h-screen flex justify-center items-center bg-gradient-to-r from-blue-100 to-blue-500">
                    <div className="bg-white p-10 rounded-lg shadow-md max-w">
                        <div className="font-bold text-7xl mb-10 text-center">Welcome {doctorProfile.username}</div>
                        <div className="grid grid-cols-2 gap-y-4 gap-x-4 text-2xl ">
                            <div className="font-semibold">SYSTEM ID:</div>
                            <div>{doctorProfile.id}</div>
                            <div className="font-semibold">Name:</div>
                            <div>{doctorProfile.name}</div>
                            <div className="font-semibold">Speciality:</div>
                            <div>{doctorProfile.speciality.name}</div>
                            <div className="font-semibold">Speciality Code:</div>
                            <div>{doctorProfile.speciality.specialityCode}</div>
                            <div className="font-semibold">Email:</div>
                            <div>{doctorProfile.email}</div>
                            <div className="font-semibold">Phone:</div>
                            <div>{doctorProfile.phone}</div>
                            <div className="font-semibold">Organization:</div>
                            <div>{doctorProfile.organization.name}</div>
                            <div className="font-semibold">Registration Number:</div>
                            <div>{doctorProfile.registrationNum}</div>
                            <div className="font-semibold">Gender:</div>
                            <div>{doctorProfile.gender}</div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}
