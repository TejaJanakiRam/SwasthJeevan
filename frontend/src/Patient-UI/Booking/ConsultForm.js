import DropdownSelect from "./DropdownSelect.js";
import { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";


export default function ConsultForm({ token, user }) {
    const hospitalData = [
        {
            id: 1,
            name: "Apollo Delhi"
        },
        {
            id: 2,
            name: "Sarvodaya-8"
        },
        {
            id: 3,
            name: "Sarvodaya-19"
        },
        {
            id: 4,
            name: "Apollo Faridabad"
        },
        {
            id: 5,
            name: "Medanta"
        },
        {
            id: 6,
            name: "QRG"
        },
        {
            id: 7,
            name: "Fortis"
        },
        {
            id: 8,
            name: "AIIMS"
        }
    ];
    const specialityData = [
        {
            id: 1,
            name: "Cardiology",
            spec_code: "CARD001"
        },
        {
            id: 2,
            name: "Orthopedics",
            spec_code: "PED001"
        }

    ]
    const [selectedItem, setSelectedItem] = useState("");
    const [isDoctorAssigned, setIsDoctorAssigned] = useState(false);

    const addToQueue = async (evt) => {
        evt.preventDefault();
        const specialty = specialityData.find(speciality => speciality.name === selectedItem);
        const spec_code = specialty.spec_code;
        const body = {
            spec_code: spec_code,
            patient_id: user.id
        }
        console.log(body);
        try {
            const response = await axios.post('http://localhost:4000/api/queue/add_patient', body, {
                headers: {
                    Authorization: `Bearer ${token}` 
                }
            });
            console.log(response);
        } catch (error) {
            console.error("Failed to add patient:", error);
        }
    }

    useEffect(() => {
        let intervalId;

        const fetchDoctorAssignment = async () => {
            try {
                // const specialty = specialtyData.find(speciality => speciality.name === selectedItem);
                // const spec_code = specialty.spec_code;
                const response = await axios.get('http://localhost:4000/api/queue/assign_doctor', {
                    params: {
                        spec_code: "SPEC005",
                        patient_id: user.id
                    },
                    headers: {
                        Authorization: `Bearer ${token}` 
                    }
                });
                if(response.data ){
                    setIsDoctorAssigned(true);
                }
                else{
                    setIsDoctorAssigned(false);
                }
                console.log(response.data)
                console.log("isDoctorAss1: " + isDoctorAssigned);
                // if (response.data) {
                //     clearInterval(intervalId);
                // }
            } catch (error) {
                console.error("Failed to fetch doctor assignment:", error);
            }
        };

        intervalId = setInterval(fetchDoctorAssignment, 2000);
        return () => clearInterval(intervalId);
    }, [token, user]);

    return (<form className='flex flex-col items-center w-full min-h-3/4 justify-center' onSubmit={addToQueue}>
        <div className="w-full flex px-16 py-8 flex-col justify-around">

            <div className="w-full flex justify-between">
                {/* <DropdownSelect data={hospitalData} displayFieldName={"Hospital"} />  */}
                <DropdownSelect data={specialityData} displayFieldName={"Speciality"} selectedItem={selectedItem} setSelectedItem={setSelectedItem} />
            </div>

        </div>
        <button type="submit" className=" font-semibold border-2 border-blue-400 bg-blue-400 text-white rounded-xl px-10 py-2 my-8  hover:bg-blue-500 hover:border-blue-500 transition-all duration-300 shadow shadow-slate-900">Book Appointment</button>
        <Link to="/videocall">
                <button 
                    type="submit" 
                    className={`font-semibold border-2 border-blue-400 bg-blue-400 text-white rounded-xl px-10 py-2 my-8  hover:bg-blue-500 hover:border-blue-500 transition-all duration-300 shadow shadow-slate-900 ${!isDoctorAssigned && 'cursor-not-allowed opacity-50'}`}
                    disabled={!isDoctorAssigned}
                >
                    Start
                </button>
            </Link>
    </form>)
}