import { useState, useEffect } from 'react'
import MenuButton from './MenuButton.js';


export default function Navbar() {
  const [isActivated, setIsActivated] = useState(true);

  return (

    <nav className='z-10 sticky top-0 w-full sm:flex justify-between items-center sm:bg-blue-900 sm:shadow sm:shadow-slate-600'>
      <div className='logo px-4 py-3 bg-blue-900'>
        <a href='#' className='flex items-center'>
          <img src="../../img/logo.png" className=' w-10 mx-2' />
          <span className='font-blackOps font-bold text-2xl text-blue-400 sm:hidden lg:inline tracking-widest'>SwasthJeevan</span>
        </a>
      </div>
      <div className={`menu sm:flex px-4 py-3 items-center sm:opacity-100 ${isActivated ? "transform translate-y-0 block" : "transform -translate-y-96 hidden"} transition-all duration-500 bg-blue-900 z-50`}>
        <ul className='sm:flex mr-8 lg:mr-16'>
          <MenuButton text={"Home"} />
          <MenuButton text={"Appointments"} />
          <MenuButton text={"EHR"} />
          <MenuButton text={"Profile"} />
        </ul>
        <button type="submit" className=" font-semibold border-2 border-blue-400 bg-blue-900 text-blue-400 rounded-xl px-10 py-2 hover:bg-blue-400 hover:text-blue-900 transition-all duration-300 mb-2 sm:mb-0">Logout</button>
      </div>
      <div className="hamburger absolute top-0 right-0 p-4 sm:hidden cursor-pointer" onClick={() => { setIsActivated(!isActivated) }}>
        <span className={`bar bg-blue-500 block h-0.5 w-6 m-1.5 ${isActivated ? "transform translate-y-2 rotate-45" : ""} transition-all duration-500`}></span>
        <span className={`bar bg-blue-500 block h-0.5 w-6 m-1.5 ${isActivated ? "opacity-0" : ""} transition-all duration-500`}></span>
        <span className={`bar bg-blue-500 block h-0.5 w-6 m-1.5 ${isActivated ? "transform -translate-y-2 -rotate-45" : ""} transition-all duration-500`}></span>
      </div>
    </nav>


  )
}