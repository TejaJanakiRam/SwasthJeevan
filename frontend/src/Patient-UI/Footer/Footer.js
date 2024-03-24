import { FaFacebook, FaInstagram, FaLinkedin, FaTwitter } from "react-icons/fa";
import { Link } from "react-router-dom";
export default function Footer() {
    return (<footer className="py-32 bg-blue-800 grid grid-cols-3">
        <div className="text-blue-400 place-self-center">
            <a href='#' className='flex items-center my-10 px-2'>
                <img src="../../img/logo.png" className=' w-10 mx-2' />
                <span className='font-blackOps font-bold text-2xl text-blue-400  tracking-widest'>SwasthJeevan</span>
            </a>

        </div>
        <div className="text-blue-400 place-self-center">
            <div>
                <h2 className="px-4 my-6 text-xl font-semibold">Privacy</h2>
                <ul className="px-4">
                    <li className='text-lg py-1 my-1 font-semibold  border-blue-500 text-blue-400 relative overflow-hidden hover:cursor-pointer hover:text-blue-300 group transition-all duration-500'>
                        <span className={`hidden sm:block w-full h-0.5 absolute bottom-0 -left-full group-hover:left-0 bg-blue-300  transition-position duration-500 rounded-full`}></span>
                        <Link to={"#"}>
                            {"Privacy Policy"}
                        </Link>
                    </li>
                    <li className='text-lg py-1 my-1 font-semibold  border-blue-500 text-blue-400 relative overflow-hidden hover:cursor-pointer hover:text-blue-300 group transition-all duration-500'>
                        <span className={`hidden sm:block w-full h-0.5 absolute bottom-0 -left-full group-hover:left-0 bg-blue-300  transition-position duration-500 rounded-full`}></span>
                        <Link to={"#"}>
                            {"Terms & Conditions"}
                        </Link>
                    </li>
                    <li className='text-lg py-1 my-1 font-semibold  border-blue-500 text-blue-400 relative overflow-hidden hover:cursor-pointer hover:text-blue-300 group transition-all duration-500'>
                        <span className={`hidden sm:block w-full h-0.5 absolute bottom-0 -left-full group-hover:left-0 bg-blue-300  transition-position duration-500 rounded-full`}></span>
                        <Link to={"#"}>
                            {"Terms of Use"}
                        </Link>
                    </li>
                </ul>
            </div>
        </div>

        <div className="text-blue-400 flex flex-col place-self-center justify-center">
            <h2 className="px-4 text-xl font-semibold">Follow Us</h2>
            <div className="flex">
                <span className="p-4 hover:cursor-pointer hover:text-blue-300 transition-all duration-300"><a href="#"><FaFacebook size={30} /></a></span>
                <span className="p-4 hover:cursor-pointer hover:text-blue-300 transition-all duration-300"><a href="#"><FaInstagram size={30} /></a></span>
                <span className="p-4 hover:cursor-pointer hover:text-blue-300 transition-all duration-300"><a href="#"><FaLinkedin size={30} /></a></span>
                <span className="p-4 hover:cursor-pointer hover:text-blue-300 transition-all duration-300"><a href="#"><FaTwitter size={30} /></a></span>
            </div>
        </div>
    </footer>)
}