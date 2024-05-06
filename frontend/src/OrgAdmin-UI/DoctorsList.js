import { useState, useEffect, useRef } from "react";
import DropdownSelect from "./DropdownSelect";
import axios from "axios";

export default function DoctorsList(props) {

    const [speciality, setSpeciality] = useState('');
    // console.log(props);
    return (<div className="h-5/6">
        <div className="w-full flex justify-between">
            {props.specialitiesList && <DropdownSelect data={props.specialitiesList} displayFieldName={"Speciality"} selectedItem={speciality} setSelectedItem={setSpeciality} />}
        </div>
        <ul className={`m-3 bg-white w-full mt-0 h-[90%] rounded-xl overflow-y-scroll scroll-smooth shadow-inner shadow-slate-800 z-10`} >
            {props.data.map((item) => (
                <li className={`p-4 mx-4 block text-xl bg-blue-400 text-white hover:cursor-pointer hover:bg-blue-500 my-4 rounded-xl ${speciality === '' ? "block" : (speciality === item.speciality.name ? "block" : "hidden")}`}>
                    <div className="flex justify-between items-center">

                    {item.name}
                    <button
                        className="ml-4 bg-red-500 text-white px-2 py-1 rounded hover:bg-red-600"
                        onClick={() => props.onDeleteDoctor(item.id)}
                    >
                    Delete
                    </button>
                    </div>
                </li>
            ))}
        </ul>
    </div>)
}