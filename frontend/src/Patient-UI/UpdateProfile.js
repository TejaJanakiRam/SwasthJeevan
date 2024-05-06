import axios from "axios";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function UpdateProfile(props) {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        "name": "",
        // password:"",
        "email": "",
        "phone": "",
        "gender": "",
        "dob": "",
        "address": ""
    });

    const fetchProfile = async()=> {
        try {
            const response = await axios.get('http://localhost:4000/api/patient/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}`
                }
            });
            // Populate form data with the existing profile data
            setFormData({
                "name": response.data.name,
                // password: response.data.password,
                "email": response.data.email,
                "phone": response.data.phone,
                "gender": response.data.gender,
                "dob": response.data.dob,
                "address": response.data.address
            });
        } catch (error) {
            console.error("Failed to fetch profile data:", error);
        }
    }
    // Load the current profile data when the component mounts
    useEffect(() => {
        fetchProfile();
    }, [props.token]);

    // Update form data based on user input
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
        console.log(formData);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.patch('http://localhost:4000/api/patient/update',formData, {
                // data: formData,
                headers: {
                    Authorization: `Bearer ${props.token}`
                },
            } );
            // If successful, navigate back to the profile page
            console.log("Changed data");
            navigate('/patient/profile');
        } catch (error) {
            console.error("Failed to update profile:", error);
        }
    };

    return (
        <div className="container mx-auto mt-10">
            <h2 className="text-3xl mb-6 text-center">Update Your Profile</h2>
            <form onSubmit={handleSubmit} className="max-w-md mx-auto">
                <div className="mb-4">
                    <label className="block text-lg mb-2">Name</label>
                    <input
                        type="text"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        className="border rounded px-3 py-2 w-full"
                    />
                </div>
                {/* <div className="mb-4">
                    <label className="block text-lg mb-2">Password</label>
                    <input
                        type="text"
                        name="name"
                        value={formData.password}
                        onChange={handleChange}
                        className="border rounded px-3 py-2 w-full"
                    />
                </div> */}
                <div className="mb-4">
                    <label className="block text-lg mb-2">Email</label>
                    <input
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        className="border rounded px-3 py-2 w-full"
                    />
                </div>
                <div className="mb-4">
                    <label className="block text-lg mb-2">Phone</label>
                    <input
                        type="number"
                        name="phone"
                        value={formData.phone}
                        onChange={handleChange}
                        className="border rounded px-3 py-2 w-full"
                    />
                </div>
                <div className="mb-4">
                    <label className="block text-lg mb-2">Gender</label>
                    <select
                        name="gender"
                        value={formData.gender}
                        onChange={handleChange}
                        className="border rounded px-3 py-2 w-full"
                    >
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
                <div className="mb-4">
                    <label className="block text-lg mb-2">DOB</label>
                    <input
                        type="date"
                        name="dob"
                        value={formData.dob}
                        onChange={handleChange}
                        className="border rounded px-3 py-2 w-full"
                    />
                </div>
                <div className="mb-4">
                    <label className="block text-lg mb-2">Address</label>
                    <input
                        type="text"
                        name="address"
                        value={formData.address}
                        onChange={handleChange}
                        className="border rounded px-3 py-2 w-full"
                    />
                </div>
                <button
                    type="submit"
                    className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 w-full"
                >
                    Update Profile
                </button>
            </form>
        </div>
    );
}
