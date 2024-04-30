import { IoMdArrowDropdown } from "react-icons/io";
import { useState, useRef, useEffect } from "react";
import { FaSearch } from "react-icons/fa";

export default function DropdownSelect({ data, displayFieldName, selectedItem, setSelectedItem }) {
    const [isActivated, setIsActivated] = useState(false);
    const [inputValue, setInputValue] = useState("");
    const dropdownRef = useRef(null);

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setIsActivated(false);
            }
        };

        document.addEventListener("click", handleClickOutside);
        return () => {
            document.removeEventListener("click", handleClickOutside);
        };
    }, []);

    return (
        <div className="w-full p-3 relative" ref={dropdownRef}>
            <div className="w-full block text-lg font-semibold text-blue-500">
                {`Select ${displayFieldName}`}
            </div>
            <button className="w-full text-blue-500 border-2 border-blue-500 rounded-xl py-2 px-3 focus:outline-none focus:border-blue-800 focus:text-blue-800 sm:text-sm lg:text-lg flex justify-between items-center" onClick={(e) => { e.preventDefault(); setIsActivated((prev) => !prev) }}>
                {selectedItem ? selectedItem.length > 30 ? selectedItem.substring(0, 30) + "..." : selectedItem : `Select ${displayFieldName}`}
                <IoMdArrowDropdown />
            </button>
            <ul className={`bg-blue-400 w-11/12 mt-0 max-h-60 rounded-xl overflow-y-scroll scroll-smooth ${isActivated ? "block" : "hidden"} absolute left-auto top-auto shadow-inner shadow-slate-800 z-10`} >
                {displayFieldName !== "Gender" && <div className="w-full bg-white flex items-center px-2 sticky top-0 text-blue-400 border-2 border-blue-400 rounded-tl-xl rounded-tr-xl">
                    <FaSearch />
                    <input type="text" value={inputValue} onChange={(e) => { setInputValue(e.target.value.toLowerCase()) }} placeholder={`Search ${displayFieldName}`} className="w-full p-3 bg-blue-white outline-none placeholder-blue-400 rounded-xl" />
                </div>}
                {data.map((item) => (
                    <li className={`p-3 text-white hover:cursor-pointer hover:bg-blue-500 border-b-2 border-blue-500 ${item.name.toLowerCase().startsWith(inputValue) ? "block" : "hidden"} ${item.name.toLowerCase() === selectedItem.toLowerCase() ? "bg-blue-600" : ""}`} onClick={() => { setIsActivated(false); if (item.name.toLowerCase() !== selectedItem.toLowerCase()) { setSelectedItem(item.name); setInputValue("") } }}>{item.name}</li>
                ))}
            </ul>
        </div>
    );
}
