import { MdOutlineKeyboardArrowDown } from "react-icons/md";
export default function AppointCard({ type }) {
    let id;
    let text;
    if (type === "upcoming") {
        id = 1;
        text = "Upcoming Appointments";
    }
    else if (type === "followup") {
        id = 2;
        text = "Follow Ups/Referrals";
    }
    else {
        id = 3;
        text = "Previous Appointments";
    }

    return (<div className='relative bg-white border-2 border-white my-4 mx-2 w-[400px] sm:w-[500px] lg:w-[900px] rounded-2xl shadow-lg shadow-slate-600'>
        <input id={`input${id}`} type='checkbox' className='absolute peer opacity-0' />
        <label htmlFor={`input${id}}`} className='absolute text-2xl right-4 top-5 rotate-0 peer-checked:rotate-180 transition-transform duration-300  text-blue-500'><MdOutlineKeyboardArrowDown /></label>
        <label htmlFor={`input${id}`} className='block min-w-full py-4 px-8 text-xl font-semibold text-blue-500  hover:cursor-pointer'>{text}</label>
        <div className='max-h-0 overflow-hidden peer-checked:max-h-full px-8 text-justify'>
            <div className='py-4 text-lg border-t-2 border-blue-400 text-blue-500'>Appointment1</div>
            <div className='py-4 text-lg border-t-2 border-blue-400 text-blue-500'>Appointment2</div>
        </div>
    </div>)
}