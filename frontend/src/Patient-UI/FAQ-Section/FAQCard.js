import { useState, useEffect } from 'react'
import { IoMdArrowDropdownCircle } from "react-icons/io";
import { MdOutlineKeyboardArrowDown } from "react-icons/md";


export default function FAQCard({ item }) {
    return (<div className='relative bg-blue-100 border-2 border-blue-400 m-2 w-[400px] sm:w-[600px] lg:w-[1000px] rounded-2xl'>
        <input id={`input${item.id}`} type='checkbox' className='absolute peer opacity-0' />
        <label htmlFor={`input${item.id}`} className='absolute text-2xl right-4 top-5 rotate-0 peer-checked:rotate-180 transition-transform duration-300 opacity-50 text-blue-800'><MdOutlineKeyboardArrowDown /></label>
        <label htmlFor={`input${item.id}`} className='block min-w-full py-4 px-8 text-xl font-semibold  '>{item.question}</label>
        <div className='max-h-0 overflow-hidden peer-checked:max-h-full px-8 text-justify'>
            <p className='py-4 text-lg border-t-2 border-blue-400'>{item.answer}</p>
        </div>
    </div>)
}