import { useState, useEffect } from 'react'
import MenuButton from './MenuButton.js';


export default function Navbar(props) {
  const navOptions = {"patient":[
    {
      id: 1,
      text: "Home",
      link: "/patient/dashboard"
    },
    {
      id: 2,
      text: "Appointments",
      link: "/patient/appointments"
    },
    {
      id: 3,
      text: "EHR",
      link: "/patient/dashboard"
    },
    {
      id: 4,
      text: "Profile",
      link: "/patient/dashboard"
    },
  ],
  "doctor": [
    {
      id: 1,
      text: "Home",
      link: "/doctor/dashboard"
    },
    {
      id: 2,
      text: "Patients",
      link: "/doctor/patients"
    },
    {
      id: 3,
      text: "Profile",
      link: "/doctor/dashboard"
    }
  ], 
  "sys_admin": [
    {
      id:1 ,
      text: "Home",
      link: "/sys_admin/dashboard"
    },
    {
      id :2 ,
      text : "Organization",
      link: "/sys_admin/organization"
    },
    {
      id:3,
      text: "Profile",
      link: "sys_admin/dashboard"
    }
  ]

}
  const [isActivated, setIsActivated] = useState(true);
  return (<header className='z-10 sticky top-0'>
    <nav className='w-full sm:flex justify-between items-center bg-white sm:shadow sm:shadow-slate-800'>
      <div className='logo px-4 py-3 bg-white'>
        <a href='#' className='flex items-center'>
          <img src="../../img/logo.png" className=' w-10 mx-2' />
          <span className='font-blackOps font-bold text-2xl text-blue-400 sm:hidden lg:inline tracking-widest'>SwasthJeevan</span>
        </a>
      </div>
      <div className={`menu sm:flex px-4 py-3 items-center sm:opacity-100 ${isActivated ? "block" : "hidden"} transition-all duration-500 bg-white z-50`}>
        <ul className='sm:flex mr-8 lg:mr-16'>
          {navOptions[props.role].map((item) => <MenuButton key={item.id} text={item.text} link={item.link} />)}
        </ul>
        <button type="submit" className=" font-semibold border-2 border-blue-400 bg-blue-400 text-white rounded-xl px-10 py-2 hover:bg-blue-500 hover:border-blue-500 transition-all duration-300 mb-2 sm:mb-0 shadow shadow-slate-900" onClick={() => { props.onLogout() }}>Logout</button>
      </div>
      <div className="hamburger absolute top-0 right-0 p-4 sm:hidden cursor-pointer" onClick={() => { setIsActivated((prev) => !prev) }}>
        <span className={`bar bg-blue-500 block h-0.5 w-6 m-1.5 ${isActivated ? "transform translate-y-2 rotate-45" : ""} transition-all duration-500`}></span>
        <span className={`bar bg-blue-500 block h-0.5 w-6 m-1.5 ${isActivated ? "opacity-0" : ""} transition-all duration-500`}></span>
        <span className={`bar bg-blue-500 block h-0.5 w-6 m-1.5 ${isActivated ? "transform -translate-y-2 -rotate-45" : ""} transition-all duration-500`}></span>
      </div>
    </nav>
  </header>


  )
}