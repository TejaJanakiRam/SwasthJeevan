import Sidebar from "../Common-UI/Sidebar/Sidebar";

export default function DoctorsList(props) {
    return (
        <div>
            <Sidebar role={"org_admin"} onLogout={props.onLogout} />
            <div className="bg-blue-400 h-full">
                
            </div>
        </div>)
}