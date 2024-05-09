import Navbar from "../Common-UI/Navbar/Navbar.js"
import EHRCard from "./EHR/EHRCard.js";


export default function EHR(props) {
    return (<div>
        <Navbar role={"patient"} onLogout={props.onLogout} />
        <div className="min-h-screen flex justify-center items-center bg-gradient-to-r from-blue-100  to-blue-500">
            <div className="tab-section  bg-white  w-[600px] lg:w-[1000px] h-[600px] rounded-2xl shadow-lg shadow-slate-800 flex flex-col items-center">
                <EHRCard type={"addehr"} />
                <EHRCard type={"manageehr"} />
            </div>
        </div>
    </div>)
}
