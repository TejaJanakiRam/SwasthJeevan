import React, { useState } from 'react';
import SignupPage from "./SignUpPage.js"
import LoginPage from './LoginPage.js';
import RoleButton from './RoleButton.js';


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

export default function AuthenticationPage(props) {
    const [role, setRole] = useState('patient');
    const [showSignup, setShowSignup] = useState(false);

    const handleRoleSelection = (selectedRole) => {
        setRole(selectedRole);
        if (role !== 'patient') {
            setShowSignup(false);
        }
    };

    const showSignupTrigger = () => {
        setShowSignup(prevState => !prevState);
    };


    const authFormProps = {
        ...props,
        role: role,
        showSignup: showSignup,
        showSignupTrigger: showSignupTrigger,
    }

    return (
        <div className="grid grid-cols-12 min-h-screen">
            <div className="col-span-12 lg:col-span-4  bg-white flex flex-col lg:shadow-inner lg:shadow-slate-800">
                <div className="flex flex-col items-center text-blue-500 ">
                    <h1 className='font-blackOps my-20 flex items-center px-2 font-bold'>
                        <img src="../../img/logo.png" className='w-12 sm:w-16 lg:w-12 2xl:w-16 mx-2' />
                        <span className='text-3xl sm:text-5xl lg:text-3xl 2xl:text-5xl tracking-widest'>SwasthJeevan</span>
                    </h1>
                    <h2 className="text-3xl sm:text-4xl lg:text-3xl xl:text-4xl mb-8 w-1/2 text-center font-semibold">Sign In As</h2>
                    <div className="flex flex-col space-y-8 w-3/4 md:w-1/2 lg:w-3/4">
                        <RoleButton role={"patient"} handleRoleSelection={handleRoleSelection} getRoleString={getRoleString} />
                        <RoleButton role={"doctor"} handleRoleSelection={handleRoleSelection} getRoleString={getRoleString} />
                        <RoleButton role={"orgadmin"} handleRoleSelection={handleRoleSelection} getRoleString={getRoleString} />
                        <RoleButton role={"sysadmin"} handleRoleSelection={handleRoleSelection} getRoleString={getRoleString} />
                    </div>
                </div>
            </div>
            <div className="hidden lg:shadow-xl lg:shadow-slate-600 lg:col-span-8  lg:flex flex-col justify-center items-center bg-gradient-to-r from-blue-200  to-blue-500">
                <div className='w-112 min-h-128 bg-white text-blue-500 px-10 py-8 xl:p-8 rounded-xl shadow-2xl shadow-slate-800'>
                    <h1 className="text-3xl sm:text-4xl lg:text-3xl xl:text-4xl font-bold mb-10 w-full text-center">{`${getRoleString(role)} Login`}</h1>
                    {showSignup ? (
                        <SignupPage {...authFormProps} />

                    ) : (

                        <LoginPage {...authFormProps} />
                    )}
                </div>
            </div>
        </div>
    );
}


