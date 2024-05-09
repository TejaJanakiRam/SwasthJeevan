import Navbar from "../Common-UI/Navbar/Navbar.js"
import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function RequestConsent(props) {
    const navigate = useNavigate();

    const [consent, setFormData] = useState({
        "type": "",
        "patientId": "",
        "doctorId": "",
        "ehrId": "",
        "status": "REQUESTED",
        "startDate": "",
        "endDate": ""
    });
    
    // Update consent data based on user input
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...consent,
            [name]: value
        });
        console.log(consent);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const getDetailsResp = await axios.get('http://localhost:4000/api/doctor/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            consent.doctorId = getDetailsResp.data.id;
            const curConsultation = await axios.get('http://localhost:4000/api/consultation_request/', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                },
                params: {
                    doctorIdStr: consent.doctorId.toString(),
                    statusStr: 'BOOKED'
                }
            });
            consent.patientId = curConsultation.data.consultationRequests[0].patientId;
            const response = await axios.post('http://localhost:4000/api/consent/', consent, {
                headers: {
                    Authorization: `Bearer ${props.token}`
                },
            } );
            if (response.status === 200)
                console.log("Requested Consent sucessfully");
            else
                console.log("Request Consent failed");
            navigate('/doctor/request_consent');
        } catch (error) {
            console.error("Failed to request consent:", error);
        }
    };

    return (
        <div>
        <Navbar role={"doctor"} onLogout={props.onLogout} />
        {(
            <div className="min-h-screen flex justify-center items-center bg-gradient-to-r from-blue-100 to-blue-500">
                <div className="bg-white p-10 rounded-lg shadow-md max-w">
                    <div className="font-bold text-7xl mb-10 text-center">Request Consent</div>
                    <div className="container mx-auto mt-10">
                        <form onSubmit={handleSubmit} className="max-w-md mx-auto">
                            <div className="mb-4">
                                <label className="block text-lg mb-2">Consent Type</label>
                                <input
                                    type="text"
                                    name="type"
                                    value={consent.type}
                                    onChange={handleChange}
                                    className="border rounded px-3 py-2 w-full"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-lg mb-2">EHR ID</label>
                                <input
                                    type="text"
                                    name="ehrId"
                                    value={consent.ehrId}
                                    onChange={handleChange}
                                    className="border rounded px-3 py-2 w-full"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-lg mb-2">Start Date</label>
                                <input
                                    type="text"
                                    name="startDate"
                                    value={consent.startDate}
                                    onChange={handleChange}
                                    className="border rounded px-3 py-2 w-full"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-lg mb-2">End Date</label>
                                <input
                                    type="text"
                                    name="endDate"
                                    value={consent.endDate}
                                    onChange={handleChange}
                                    className="border rounded px-3 py-2 w-full"
                                />
                            </div>
                            <button
                                type="submit"
                                className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 w-full"
                            >
                                Request Consent
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        )}
        </div>
    );
}
