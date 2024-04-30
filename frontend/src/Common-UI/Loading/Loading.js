import { FaSpinner } from "react-icons/fa6";

export default function Loading() {
    return (<div className="h-full flex justify-center items-center">
        <FaSpinner size={40} className="animate-spin motion-reduce:animate-[spin_1.5s_linear_infinite] text-blue-400" />
    </div>)
}