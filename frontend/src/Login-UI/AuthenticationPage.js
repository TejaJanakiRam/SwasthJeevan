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
        if(role !== 'patient'){
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
                <div className="flex flex-col items-center text-blue-500 font-bold ">
                    <h1 className='font-blackOps my-20 flex items-center px-2'>
                        <span className='text-4xl sm:text-5xl lg:text-4xl 2xl:text-5xl'>SwasthJeevan</span>
                        <img src="../../img/logo.png" className='w-16 sm:w-24 lg:w-16 2xl:w-24' /></h1>
                    <h2 className="text-3xl sm:text-4xl lg:text-3xl xl:text-4xl mb-8 w-1/2 text-center">Sign In As</h2>
                    <div className="flex flex-col space-y-8 w-3/4 md:w-1/2 lg:w-3/4">
                        <RoleButton role={"patient"} handleRoleSelection={handleRoleSelection} getRoleString={getRoleString} faIconString={"fa-user"}/>
                        <RoleButton role={"doctor"} handleRoleSelection={handleRoleSelection} getRoleString={getRoleString} faIconString={"fa-user-doctor"}/>
                        <RoleButton role={"orgadmin"} handleRoleSelection={handleRoleSelection} getRoleString={getRoleString} faIconString={"fa-hospital-user"}/>
                        <RoleButton role={"sysadmin"} handleRoleSelection={handleRoleSelection} getRoleString={getRoleString} faIconString={"fa-user-gear"}/>
                    </div>
                </div>
            </div>
            <div className="hidden lg:shadow-xl lg:shadow-slate-600 lg:col-span-8  lg:flex flex-col justify-center items-center bg-gradient-to-r from-blue-200  to-blue-500">
                <div className='w-1/2 xl:w-1/3 max-w-screen-sm bg-white text-blue-500 py-6 px-10 rounded-xl shadow-2xl shadow-slate-800 '>
                    <h1 className="text-3xl sm:text-4xl lg:text-3xl xl:text-4xl font-bold mb-16 w-full text-center">{`${getRoleString(role)} Login`}</h1>
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


