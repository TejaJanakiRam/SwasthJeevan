import { useNavigate } from "react-router-dom";
import { MdOutlineKeyboardArrowDown } from "react-icons/md";

export default function ConsentCard({ type }) {
    const navigate = useNavigate();
    const handleProvideConsent = async()=>{
        try {
            navigate('/patient/consent/provide');
        } catch (error) {
            console.error("Failed to provide Consent:", error);
        }
    }
    const handleRevokeConsent = async()=>{
        try {
            navigate('/patient/consent/revoke');
        } catch (error) {
            console.error("Failed to revoke Consent:", error);
        }
    }
    let id;
    let text;
    let clickfunc;
    if (type === "provideconsent") {
        id = 1;
        text = "Provide Consent";
        type= "text"
        clickfunc=handleProvideConsent
    }
    else if (type === "revokeconsent") {
        id = 2;
        text = "Revoke Consent";
        type= "text"
        clickfunc=handleRevokeConsent
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
