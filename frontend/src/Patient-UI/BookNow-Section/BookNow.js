import { FaVideo } from "react-icons/fa";
import { FaHospitalUser } from "react-icons/fa";
import { FaFilePrescription } from "react-icons/fa";
export default function BookNow(props) {
    return (<div className="min-h-[600px]  flex flex-col justify-around items-center bg-blue-200">
        <h2 className="text-4xl text-blue-500 tracking-widest ">{`Welcome ${props.username} !`.toUpperCase()}</h2>
        <div className="working flex relative w-[400px] sm:w-[600px] lg:w-[1000px] justify-center items-center">
            <div className="h-[60px] w-[60px] sm:h-[80px] sm:w-[80px] bg-blue-500 rounded-full   flex items-center justify-center text-2xl sm:text-4xl text-blue-100"><FaHospitalUser /></div>
            <div className="w-[80px] sm:w-[200px] lg:w-[400px] h-[4px] bg-blue-500"></div>
            <div className="h-[60px] w-[60px] sm:h-[80px] sm:w-[80px] bg-blue-500 rounded-full  flex items-center justify-center text-2xl sm:text-4xl text-blue-100"><FaVideo /></div>
            <div className="w-[80px] sm:w-[200px] lg:w-[400px] h-[4px] bg-blue-500"></div>
            <div className="h-[60px] w-[60px] sm:h-[80px] sm:w-[80px] bg-blue-500 rounded-full flex items-center justify-center text-2xl sm:text-4xl text-blue-100"><FaFilePrescription /></div>
        </div>
        <button className="text-xl sm:text-2xl  px-4 py-3 sm:px-8 sm:py-6 rounded-2xl border-2 border-blue-500 text-blue-500 hover:bg-blue-500 hover:text-blue-200 transition-all duration-300 font-semibold tracking-wide sm:tracking-widest">Book Appointment / Consult Now</button>

    </div>)
}