import { FaChevronCircleLeft, FaChevronCircleRight } from "react-icons/fa";
import SpecialityCard from "./SpecialityCard.js";


export default function Specialities({ data }) {

    return (
        <div className='py-8 my-4 border-t-2 border-b-2'>
            <h2 className='text-center text-4xl mb-4 font-semibold text-blue-400'>Top Specialities</h2>
            <div className='flex items-center relative my-4 h-[180px] sm:h-[200px] bg-blue-100 rounded-xl mx-4 sm:mx-16  border-2 border-blue-400 '>
                <div className='hidden sm:flex text-2xl lg:text-3xl px-2 lg:px-3 hover:cursor-pointer h-full items-center  opacity-50 hover:opacity-100 transition-all duration-300 hover:scale-105' onClick={() => { const slider = document.getElementById("slider"); slider.scrollLeft -= 500 }}>
                    <FaChevronCircleLeft />
                </div>
                <div id="slider" className='slider flex overflow-x-scroll scroll whitespace-nowrap scroll-smooth z-0 scrollbar-hide border-r-2 border-l-2 border-blue-200'>
                    {data.map((item) => <SpecialityCard key={item.id} item={item} />)}
                </div>
                <div className='hidden sm:flex text-2xl lg:text-3xl px-2 lg:px-3 hover:cursor-pointer h-full items-center opacity-50 hover:opacity-100 transtion-all duration-300 hover:scale-105' onClick={() => { const slider = document.getElementById("slider"); slider.scrollLeft += 500 }}>
                    <FaChevronCircleRight />
                </div>
            </div>
        </div>
    )
}