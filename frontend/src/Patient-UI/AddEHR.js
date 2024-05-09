import Navbar from "../Common-UI/Navbar/Navbar.js"
import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AddEHR(props) {
    const navigate = useNavigate();

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
        getDetails()
    }, []);

    const [ehrMetadata, setFormData] = useState({
        "userId": "",
        "type": "",
        "diagnosisType": "",
        "issueDate": "",
        "endDate": ""
    });
    let document;
    
    // Update ehrMetadata based on user input
    const handleChange = (e) => {
        ehrMetadata.userId = patientProfile.id;
        const { name, value } = e.target;
        setFormData({
            ...ehrMetadata,
            [name]: value
        });
        console.log(ehrMetadata);
    };

    // On file select (from the pop up)
    const handleFileChange = (e) => {
        document = e.target.files[0]
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        //formData.append("ehrMetadata", new Blob([JSON.stringify(ehrMetadata)], { type: "application/json" }));
        //formData.append("document", new Blob([document], { type: 'application/octet-stream' }));
        formData.append("ehrMetadata", new Blob([JSON.stringify(ehrMetadata)], { type: "application/json" }));
        formData.append("document", document);
        try {
            const response = await axios.post('http://localhost:4000/api/ehr/', formData, {
                headers: {
                    Authorization: `Bearer ${props.token}`,
                    //'Content-Type': 'multipart/form-data',
                },
            } );
            // If successful, navigate back to the profile page
            if (response.status === 200)
                console.log("Added EHR sucessfully");
            else
                console.log("Add EHR failed");
            navigate('/patient/ehr/add');
        } catch (error) {
            console.error("Failed to add ehr:", error);
        }
    };

    return (
        <div>
        <Navbar role={"patient"} onLogout={props.onLogout} />
        {(
            <div className="min-h-screen flex justify-center items-center bg-gradient-to-r from-blue-100 to-blue-500">
                <div className="bg-white p-10 rounded-lg shadow-md max-w">
                    <div className="font-bold text-7xl mb-10 text-center">Add EHR</div>
                    <div className="container mx-auto mt-10">
                        <form onSubmit={handleSubmit} className="max-w-md mx-auto">
                            <div className="mb-4">
                                <label className="block text-lg mb-2">EHR Type</label>
                                <input
                                    type="text"
                                    name="type"
                                    value={ehrMetadata.type}
                                    onChange={handleChange}
                                    className="border rounded px-3 py-2 w-full"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-lg mb-2">Diagnosis</label>
                                <input
                                    type="text"
                                    name="diagnosisType"
                                    value={ehrMetadata.diagnosisType}
                                    onChange={handleChange}
                                    className="border rounded px-3 py-2 w-full"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-lg mb-2">Issue Date</label>
                                <input
                                    type="text"
                                    name="issueDate"
                                    value={ehrMetadata.issueDate}
                                    onChange={handleChange}
                                    className="border rounded px-3 py-2 w-full"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-lg mb-2">End Date</label>
                                <input
                                    type="text"
                                    name="endDate"
                                    value={ehrMetadata.endDate}
                                    onChange={handleChange}
                                    className="border rounded px-3 py-2 w-full"
                                />
                            </div>
                            <div className="mb-4">
                                <label className="block text-lg mb-2">End Date</label>
                                <input
                                    type="file"
                                    name="document"
                                    onChange={handleFileChange}
                                    className="border rounded px-3 py-2 w-full"
                                />
                            </div>
                            <button
                                type="submit"
                                className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 w-full"
                            >
                                Add EHR
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        )}
        </div>
    );
}
