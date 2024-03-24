import Navbar from "../Common-UI/Navbar/Navbar.js"
export default function PatientList(props) {
    return (<div><Navbar role={"doctor"} onLogout={props.onLogout} />
    <div>Hello</div> </div>)
}