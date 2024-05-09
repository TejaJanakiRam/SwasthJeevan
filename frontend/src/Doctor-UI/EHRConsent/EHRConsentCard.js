import { useNavigate } from "react-router-dom";
import { MdOutlineKeyboardArrowDown } from "react-icons/md";

export default function EHRConsentCard({ type }) {
    const navigate = useNavigate();
    const handleRequestConsent = async()=>{
        try {
            navigate('/doctor/request_consent');
        } catch (error) {
            console.error("Failed to request consent for EHR:", error);
        }
    }
    const handleViewConsents = async()=>{
        try {
            navigate('/doctor/view_consents');
        } catch (error) {
            console.error("Failed to view consents:", error);
        }
    }
    let id;
    let text;
    let clickfunc;
    if (type === "requestconsent") {
        id = 1;
        text = "Request Consent";
        type= "text"
        clickfunc=handleRequestConsent
    }
    else if (type === "viewconsents") {
        id = 2;
        text = "View Consents";
        type= "text"
        clickfunc=handleViewConsents
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
