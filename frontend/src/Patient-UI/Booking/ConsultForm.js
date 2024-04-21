import DropdownSelect from "./DropdownSelect.js";
import { useState } from "react";
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
    const specialtyData = [
        {
            id: 1,
            name: "Cardiology",
            spec_code: "SPEC001"
        },
        {
            id: 2,
            name: "Orthopedics",
            spec_code: "SPEC005"
        }

    ]
    const [selectedItem, setSelectedItem] = useState("");

    const addToQueue = async (evt) => {
        evt.preventDefault();
        const specialty = specialtyData.find(speciality => speciality.name === selectedItem);
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


    return (<form className='flex flex-col items-center w-full min-h-3/4 justify-center' onSubmit={addToQueue}>
        <div className="w-full flex px-16 py-8 flex-col justify-around">

            <div className="w-full flex justify-between">
                {/* <DropdownSelect data={hospitalData} displayFieldName={"Hospital"} />  */}
                <DropdownSelect data={specialtyData} displayFieldName={"Speciality"} selectedItem={selectedItem} setSelectedItem={setSelectedItem} />
            </div>

        </div>
        <button type="submit" className=" font-semibold border-2 border-blue-400 bg-blue-400 text-white rounded-xl px-10 py-2 my-8  hover:bg-blue-500 hover:border-blue-500 transition-all duration-300 shadow shadow-slate-900">Book Appointment</button>
        <Link to="/videocall"><button type="submit" className=" font-semibold border-2 border-blue-400 bg-blue-400 text-white rounded-xl px-10 py-2 my-8  hover:bg-blue-500 hover:border-blue-500 transition-all duration-300 shadow shadow-slate-900">Start</button></Link>
    </form>)
}