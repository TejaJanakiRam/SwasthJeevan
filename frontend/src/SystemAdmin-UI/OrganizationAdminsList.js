import { useState } from "react";
import { FaSearch } from "react-icons/fa";
export default function OrganizationAdminsList(props) {
    const [inputValue, setInputValue] = useState("");
    return (<div className=" h-5/6">
        <ul className={`bg-white w-full mt-0 h-full rounded-xl overflow-y-scroll scroll-smooth shadow-inner shadow-slate-800 z-10`} >
            {props.data.map((item) => (
                <li className={`p-4 mx-8 block text-xl bg-blue-400 text-white hover:cursor-pointer hover:bg-blue-500 my-4 rounded-xl`}>{item.name}</li>
            ))}
        </ul>
    </div>)
}