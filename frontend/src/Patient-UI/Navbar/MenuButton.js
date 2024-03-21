
import { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
export default function MenuButton({ text, link }) {


    return (<li className='text-lg mx-2 mb-8 sm:mb-0 sm:mx-3 sm:px-3 sm:py-2 font-semibold  border-b-1 border-blue-500 text-blue-400 relative overflow-hidden hover:cursor-pointer hover:text-blue-500 group transition-all duration-500'>
        <span className={`hidden sm:block w-full h-0.5 absolute bottom-0 -left-full group-hover:left-0 bg-blue-500  transition-position duration-500 rounded-full`}></span>
        <Link to={link}>
            {text}
        </Link>
    </li>)
}