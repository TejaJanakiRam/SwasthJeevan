import { useState } from "react";
import { FaSearch } from "react-icons/fa";
export default function OrganizationsList(props) {
    const [inputValue, setInputValue] = useState("");
    return (<div className=" h-5/6">
        <div className="w-full my-3 bg-white flex items-center px-2 sticky top-0 text-blue-400 border-2 border-blue-400 rounded-xl">
            <FaSearch />
            <input type="text" value={inputValue} onChange={(e) => { setInputValue(e.target.value.toLowerCase()) }} placeholder={`Search Organizations`} className="w-full p-3 bg-blue-white outline-none placeholder-blue-400 rounded-xl" />
        </div>
        <ul className={`bg-white w-full mt-0 h-full rounded-xl overflow-y-scroll scroll-smooth shadow-inner shadow-slate-800 z-10`} >
            {props.data.map((item) => (
                <li className={`p-4 mx-8 block text-xl bg-blue-400 text-white hover:cursor-pointer hover:bg-blue-500 my-4 rounded-xl ${item.name.toLowerCase().startsWith(inputValue) ? "block" : "hidden"}`}>{item.name}</li>
            ))}
        </ul>
    </div>)
}