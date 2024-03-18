import React, { useState } from 'react';
import { FaUser, FaUserDoctor, FaHospitalUser, FaUserGear, FaArrowRightLong } from "react-icons/fa6";



export default function RoleButton({ role, handleRoleSelection, getRoleString}) {
    const roleIcons = {
        'user': FaUser,
        'doctor': FaUserDoctor,
        'orgadmin': FaHospitalUser,
        'sysadmin': FaUserGear
    };

    const RoleIcon = roleIcons[role] || FaUser;
    return (<button type="button" className="bg-white border-2 border-opacity-50 border-blue-500 rounded-xl p-4 flex items-center justify-between hover:bg-blue-500 hover:text-white transition-all duration-300 font-semibold" onClick={() => handleRoleSelection(role)}>
        <div className='flex items-center'>
            <span className='text-xl w-10'>
                <RoleIcon />
            </span>
            <span className='text-lg'>{getRoleString(role)}</span>
        </div>
        <div className='text-xl'><FaArrowRightLong /></div>
    </button>)
}