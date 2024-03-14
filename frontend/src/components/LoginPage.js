import React, { useState } from 'react';
import axios from "axios";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faUser, faUserDoctor, faHospitalUser, faUserGear, faCircleArrowRight } from '@fortawesome/free-solid-svg-icons'
import SignupPage from "./SignUpPage.js"

library.add(faUser, faUserDoctor, faHospitalUser, faUserGear, faCircleArrowRight)

function getRoleString(role) {
    switch (role) {
        case "patient":
            return ("Patient");
        case "doctor":
            return ("Doctor");
        case "orgadmin":
            return ("Organisation Admin");
        case "sysadmin":
            return ("System Admin");
        default:
            return ("");
    }
}

function LoginPage(props) {
    const [role, setRole] = useState('patient');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [showSignup, setShowSignup] = useState(false);

    const handleRoleSelection = (selectedRole) => {
        setRole(selectedRole);
        setShowSignup(false);
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        try {

            const response = await axios.post('http://localhost:4000/auth/signin', {
                username,
                password,
                role: role.toUpperCase()
            });
            console.log(response.data.jwt);
            const token = String(response.data.jwt);
            props.onLogin(token, role.toUpperCase());
        } catch (error) {
            alert("Wrong credentials!")
        }

    };

    const handleForgotPassword = () => {
        console.log('Forgot password');
    };

    const showSignupTrigger = () => {
        setShowSignup(prevState => !prevState);
    };

    return (
        <div className="grid grid-cols-12 min-h-screen">
            <div className="col-span-12 lg:col-span-4  bg-white flex flex-col lg:shadow-inner lg:shadow-slate-800">
                <div className="flex flex-col items-center text-blue-500 font-bold ">
                    <h1 className='font-blackOps my-20 flex items-center px-2'>
                        <span className='text-4xl sm:text-5xl lg:text-4xl 2xl:text-5xl'>SwasthJeevan</span>
                        <img src="../../img/logo.png" className='w-16 sm:w-24 lg:w-16 2xl:w-24' /></h1>
                    <h2 className="text-3xl sm:text-4xl lg:text-3xl xl:text-4xl mb-8 w-1/2 text-center">Sign In As</h2>
                    <div className="flex flex-col space-y-8 w-3/4 md:w-1/2 lg:w-3/4">
                        <button type="button" className="bg-white border-2 border-blue-500 rounded-xl p-4 flex justify-between hover:bg-blue-500 hover:text-white transition-all duration-300" onClick={() => handleRoleSelection('patient')}>
                            <div>
                                <span className='xl:text-2xl lg:text-xl'>
                                    <FontAwesomeIcon className="mr-4" icon="fa-solid fa-user" />
                                </span>
                                <span className='sm:text-lg xl:text-xl'>Patient</span>
                            </div>
                            <div className='xl:text-2xl lg:text-xl'><FontAwesomeIcon icon="fa-solid fa-circle-arrow-right" /></div>
                        </button>
                        <button type="button" className="bg-white border-2 border-blue-500 rounded-xl p-4 flex justify-between hover:bg-blue-500 hover:text-white transition-all duration-300" onClick={() => handleRoleSelection('doctor')}>
                            <div>
                                <span className='xl:text-2xl lg:text-xl'>
                                    <FontAwesomeIcon className="mr-4" icon="fa-solid fa-user-doctor" />
                                </span>
                                <span className='sm:text-lg xl:text-xl'>Doctor</span>
                            </div>
                            <div className='xl:text-2xl lg:text-xl'><FontAwesomeIcon icon="fa-solid fa-circle-arrow-right" /></div>
                        </button>
                        <button type="button" className="bg-white border-2 border-blue-500 rounded-xl p-4 flex justify-between hover:bg-blue-500 hover:text-white transition-all duration-300" onClick={() => handleRoleSelection('orgadmin')}>
                            <div>
                                <span className='xl:text-2xl lg:text-xl'>
                                    <FontAwesomeIcon className="mr-4" icon="fa-solid fa-hospital-user" />
                                </span>
                                <span className='sm:text-lg xl:text-xl'>Organisation Admin</span>
                            </div>
                            <div className='xl:text-2xl lg:text-xl'><FontAwesomeIcon icon="fa-solid fa-circle-arrow-right" /></div>
                        </button>
                        <button type="button" className="bg-white border-2 border-blue-500 rounded-xl p-4 flex justify-between hover:bg-blue-500 hover:text-white transition-all duration-300" onClick={() => handleRoleSelection('sysadmin')}>
                            <div>
                                <span className='xl:text-2xl lg:text-xl'>
                                    <FontAwesomeIcon className="mr-4" icon="fa-solid fa-user-gear" />
                                </span>
                                <span className='sm:text-lg xl:text-xl'>System Admin</span>
                            </div>
                            <div className='xl:text-2xl lg:text-xl'><FontAwesomeIcon icon="fa-solid fa-circle-arrow-right" /></div>
                        </button>

                    </div>
                </div>
            </div>
            <div className="hidden lg:shadow-xl lg:shadow-slate-600 lg:col-span-8  lg:flex flex-col justify-center items-center bg-gradient-to-r from-blue-200  to-blue-500">
                <div className='w-1/2 xl:w-1/3 max-w-screen-sm bg-white text-blue-500 py-6 px-10 rounded-xl shadow-2xl shadow-slate-800 '>
                    <h1 className="text-3xl sm:text-4xl lg:text-3xl xl:text-4xl font-bold mb-16 w-full text-center">{`${getRoleString(role)} Login`}</h1>
                    {showSignup ? (
                        <SignupPage role={role} showSignUpTrigger={showSignupTrigger} />
                    ) : (
                        <form onSubmit={handleLogin} className='flex flex-col items-center space-y-5 w-full'>
                            <div className="w-full">
                                <label htmlFor="username" className="block sm:text-lg xl:text-xl py-1 font-semibold">Username</label>
                                <input type="text" id="username" name="username" placeholder='Enter Username' className="w-full border-2 border-blue-500 rounded-xl py-2 px-3 focus:outline-none  focus:border-blue-800 sm:text-sm xl:text-lg text-blue-800" autoComplete="off" onChange={(e) => setUsername(e.target.value)} />
                            </div>
                            <div className="w-full">
                                <label htmlFor="password" className="block sm:text-lg xl:text-xl py-1 font-semibold">Password</label>
                                <input type="password" id="password" name="password" placeholder="Enter Password" className="w-full border-2 border-blue-500 rounded-xl py-2 px-3 focus:outline-none focus:border-blue-800 sm:text-sm xl:text-lg text-blue-800" autoComplete="off" onChange={(e) => setPassword(e.target.value)} />
                            </div>


                            <div className='self-end'>
                                <a href="#" className="underline text-blue-500 sm:text-sm xl:text-lg" onClick={handleForgotPassword}>Forgot Password?</a>
                            </div>
                            <button type="submit" className=" border-2 sm:text-lg xl:text-xl border-blue-500 text-blue-500 font-bold rounded-xl p-1 xl:p-2 w-1/2 hover:bg-blue-500 hover:text-white transition-all duration-300">Login</button>
                            {role === 'patient' && !showSignup && (
                                <div className="self-start text-blue-500 sm:text-sm xl:text-lg">
                                    Don't have an account? <a href="#" className="underline " onClick={showSignupTrigger}>Sign up Here</a>
                                </div>
                            )}
                        </form>


                    )}
                </div>
            </div>
        </div>
    );
}

export default LoginPage;
