import { createContext, useState } from "react";
import MenuButton from "./MenuButton.js";


export default function Sidebar(props) {
    const navOptions = {
        "org_admin": [
            {
                id: 1,
                text: "Home",
                link: "/org_admin/dashboard"
            },
            {
                id: 2,
                text: "Manage Doctors",
                link: "/org_admin/doctors"
            },
            {
                id: 3,
                text: "Manage Organization",
                link: "/org_admin/organization"
            }

        ]
    };
    const [isActivated, setIsActivated] = useState(true);
    return (<aside className={`h-screen absolute bg-white rounded-tr-3xl rounded-br-3xl`}>
        <nav className={`h-full flex flex-col shadow-lg shadow-slate-800 w-fit rounded-tr-3xl rounded-br-3xl p-2`}>
            <div className="flex flex-col justify-between h-1/2">
                <div>
                    <div className={`flex ${isActivated ? "pr-8" : ""}`}>
                        <div className="hamburger py-4 px-4 cursor-pointer" onClick={() => { setIsActivated((prev) => !prev) }}>
                            <span className={`bar bg-blue-500 block h-0.5 w-6 m-1.5 ${isActivated ? "transform translate-y-2 rotate-45" : ""} transition-all duration-100`}></span>
                            <span className={`bar bg-blue-500 block h-0.5 w-6 m-1.5 ${isActivated ? "opacity-0" : ""} transition-all duration-100`}></span>
                            <span className={`bar bg-blue-500 block h-0.5 w-6 m-1.5 ${isActivated ? "transform -translate-y-2 -rotate-45" : ""} transition-all duration-100`}></span>
                        </div>
                        <a href='#' className={`flex items-center ${isActivated ? "block" : "hidden"}`}>
                            <img src="../../img/logo.png" className=' w-10 mx-2' />
                            <span className='font-blackOps font-bold text-2xl text-blue-400 sm:hidden lg:inline tracking-widest'>SwasthJeevan</span>
                        </a>
                    </div>
                    <ul className='flex flex-col mt-8'>
                        {navOptions[props.role].map((item) => <MenuButton key={item.id} text={item.text} link={item.link} isActivated={isActivated} />)}
                    </ul>
                </div>
                <button type="submit" className={`mx-4 font-semibold border-2 border-blue-400 bg-blue-400 text-white rounded-xl px-10 py-2 hover:bg-blue-500 hover:border-blue-500 transition-all duration-300 mb-2 sm:mb-0 shadow shadow-slate-900 ${isActivated ? "block" : "hidden"}`} onClick={() => { props.onLogout() }}>Logout</button>
            </div>
        </nav>
    </aside>)

}