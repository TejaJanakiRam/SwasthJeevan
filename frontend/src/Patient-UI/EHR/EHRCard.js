import { useNavigate } from "react-router-dom";
import { MdOutlineKeyboardArrowDown } from "react-icons/md";

export default function EHRCard({ type }) {
    const navigate = useNavigate();
    const handleAddEHR = async()=>{
        try {
            navigate('/patient/ehr/add');
        } catch (error) {
            console.error("Failed to add EHR:", error);
        }
    }
    const handleManageEHR = async()=>{
        try {
            navigate('/patient/ehr/manage');
        } catch (error) {
            console.error("Failed to manage EHR:", error);
        }
    }
    let id;
    let text;
    let clickfunc;
    if (type === "addehr") {
        id = 1;
        text = "Add EHR";
        type= "text"
        clickfunc=handleAddEHR
    }
    else if (type === "manageehr") {
        id = 2;
        text = "Manage EHR";
        type= "text"
        clickfunc=handleManageEHR
    }

    return (<div className='relative bg-white border-2 border-white my-4 mx-2 w-[400px] sm:w-[500px] lg:w-[900px] rounded-2xl shadow-lg shadow-slate-600'>
        <input
                        id={`input${id}`}
                        type='checkbox'
                        onChange={clickfunc}
                        className='absolute peer opacity-0'
        />
        <label htmlFor={`input${id}}`} className='absolute text-2xl right-4 top-5 rotate-0 peer-checked:rotate-180 transition-transform duration-300  text-blue-500'><MdOutlineKeyboardArrowDown /></label>
        <label htmlFor={`input${id}`} className='block min-w-full py-4 px-8 text-xl font-semibold text-blue-500  hover:cursor-pointer'>{text}</label>
    </div>)
}
