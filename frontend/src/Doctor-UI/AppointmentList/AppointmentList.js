import AppointmentCard from "./AppointmentCard.js";
export default function AppointmentList() {
    const data = [{ id: 1 }, { id: 2 }, { id: 3 }, { id: 4 }, { id: 5 }, { id: 6 }, { id: 7 }, { id: 8 }];

    return (<div className="min-h-screen flex justify-center items-center bg-gradient-to-r from-blue-100  to-blue-500">
        <div className="bg-white  w-[800px] lg:w-[1200px] h-[800px] rounded-2xl shadow-lg shadow-slate-800">
            <div className="p-3">
                <label htmlFor="date" className="w-1/2 block text-lg font-semibold text-blue-500">Date</label>
                <input type="date" id="date" name="date" className="w-1/2 text-blue-500 border-2 border-blue-500 rounded-xl py-2 px-3 focus:outline-none  focus:border-blue-800 focus:text-blue-800 sm:text-sm lg:text-lg" autoComplete="off" />
            </div>
            <div className='rounded-xl p-8 bg-white'>
                <h2 className='text-4xl mb-4 font-semibold text-blue-400'>Appointment History</h2>
                <ul className=' max-h-[600px] overflow-y-scroll scroll-smooth flex flex-col items-center border-4 border-blue-400 rounded-xl'>
                    {data.map((item) => <AppointmentCard key={item.id} item={item} />)}
                </ul>
            </div>
        </div>
    </div>)
}