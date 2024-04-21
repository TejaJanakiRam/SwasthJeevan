import { FaVideo } from "react-icons/fa";
import { FaHospitalUser } from "react-icons/fa";
import { FaFilePrescription } from "react-icons/fa";
import { Link } from "react-router-dom";
export default function BookNow({ user }) {
    return (<div className="min-h-[600px]  flex flex-col justify-around items-center bg-blue-400">
        <h2 className="text-3xl sm:text-4xl text-white font-bold tracking-widest ">{`Welcome  ${user.username} !`.toUpperCase()}</h2>
        <div className="working flex relative w-[400px] sm:w-[600px] lg:w-[1000px] justify-center items-center">
            <div className="h-[60px] w-[60px] sm:h-[80px] sm:w-[80px] bg-white rounded-full   flex items-center justify-center text-2xl sm:text-4xl text-blue-500 shadow shadow-slate-800"><FaHospitalUser /></div>
            <div className="w-[80px] sm:w-[200px] lg:w-[400px] h-[4px] bg-white shadow-lg shadow-slate-800"></div>
            <div className="h-[60px] w-[60px] sm:h-[80px] sm:w-[80px] bg-white rounded-full  flex items-center justify-center text-2xl sm:text-4xl text-blue-500 shadow shadow-slate-800"><FaVideo /></div>
            <div className="w-[80px] sm:w-[200px] lg:w-[400px] h-[4px] bg-white shadow-lg shadow-slate-800"></div>
            <div className="h-[60px] w-[60px] sm:h-[80px] sm:w-[80px] bg-white rounded-full flex items-center justify-center text-2xl sm:text-4xl text-blue-500 shadow shadow-slate-800"><FaFilePrescription /></div>
        </div>
        <Link to="/patient/booking">
            <button className="text-xl sm:text-2xl  px-4 py-3 sm:px-8 sm:py-6 rounded-2xl border-2 border-white text-blue-500 bg-white hover:text-blue-600 hover:bg-gray-100 hover:border-gray-50 transition-all duration-300 font-semibold tracking-wide sm:tracking-widest shadow shadow-slate-800">
                Consult Now
            </button>
        </Link>


    </div>)
}