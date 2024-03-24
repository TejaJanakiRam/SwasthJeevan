
export default function AppointmentCard({ item }) {
    return (<li className='bg-blue-400 border-2 border-blue-400 my-4 mx-2 w-[400px] sm:w-[600px] lg:w-[1000px] rounded-2xl shadow-lg shadow-slate-600 flex items-center justify-between p-4'>
        <span>{`Appointment ${item.id}`}</span><button className=" font-semibold border-2 border-white bg-white text-blue-400 rounded-xl px-10 py-2 hover:text-blue-500 hover:bg-gray-100 transition-all duration-300 mb-2 sm:mb-0 shadow shadow-slate-900">Start</button>
    </li>)
}