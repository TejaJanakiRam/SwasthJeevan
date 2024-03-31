import BookForm from "./Booking/BookForm.js"
import ConsultForm from "./Booking/ConsultForm.js"
import Navbar from "../Common-UI/Navbar/Navbar.js"
import { useState } from "react"

export default function Booking(props) {
    const [currentTab, setCurrentTab] = useState('book')

    return (
        <div className="min-h-screen bg-gradient-to-r from-blue-100  to-blue-500 my-auto flex flex-col">
            <Navbar role={"patient"} onLogout={props.onLogout} />

            <div className="flex-grow flex justify-center items-center ">
                <div className="tab-section  bg-white  w-[600px] lg:w-[1000px] h-[600px] rounded-2xl shadow-lg shadow-slate-800">
                    <div className="tab-buttons text-lg border border-blue-600 bg-blue-500 inline-block rounded-tl-2xl rounded-br-2xl">
                        <button className={`py-3 px-4 text-sm transition duration-300 shadow shadow-slate-800 ${currentTab === 'book' ? "text-white bg-blue-500 shadow-inner shadow-slate-600" : "text-white bg-blue-500"} rounded-tl-xl`} onClick={() => { setCurrentTab('book') }}>Book Appointment</button>
                        <button className={`py-3 px-4 text-sm transition duration-300 shadow shadow-slate-800 ${currentTab === 'consult' ? "text-white bg-blue-500 shadow-inner shadow-slate-800" : "text-white bg-blue-500  "} rounded-br-2xl`} onClick={() => { setCurrentTab('consult') }}>Consult Now</button>
                    </div>
                    <div className="tab-content h-full">
                        {currentTab === "book" ? <BookForm /> : <ConsultForm />}
                    </div>
                </div>
            </div>


        </div>
    )
}