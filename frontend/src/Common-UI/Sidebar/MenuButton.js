
import { Link } from 'react-router-dom'
import { FaUserDoctor, FaHospital, FaUserGear } from "react-icons/fa6";
import { FaHome } from "react-icons/fa";


export default function MenuButton({ text, link, isActivated }) {

    const menuIcons = {
        'Home': FaHome,
        'Manage Organization': FaHospital,
        'Manage Organizations': FaHospital,
        'Manage Organization Admins': FaUserGear,
        'Manage Doctors': FaUserDoctor,
    };
    const MenuIcon = menuIcons[text] || FaHome;
    return (<li className='text-lg font-semibold'>
        <Link className="block mx-2 my-4" to={link}>
            <div className={`flex h-[50px] ${isActivated ? "" : " w-[50px] justify-center"} p-4 text-blue-400 items-center bg-blue-100 rounded-xl hover:bg-blue-200 hover:text-blue-500 hover:cursor-pointer  transition-colors duration-300`}>
                <span className={`text-3xl block`}>
                    <MenuIcon />
                </span>
                <span className={`${isActivated ? "pl-4 block" : "hidden"}`}>{text}</span>
            </div>
        </Link>
    </li>)
}