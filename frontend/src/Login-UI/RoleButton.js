import React, { useState } from 'react';
import axios from "axios";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faUser, faUserDoctor, faHospitalUser, faUserGear, faCircleArrowRight } from '@fortawesome/free-solid-svg-icons'
library.add(faUser, faUserDoctor, faHospitalUser, faUserGear, faCircleArrowRight)

export default function RoleButton({role, handleRoleSelection, getRoleString, faIconString}){
    return (<button type="button" className="bg-white border-2 border-blue-500 rounded-xl p-4 flex justify-between hover:bg-blue-500 hover:text-white transition-all duration-300" onClick={() => handleRoleSelection(role)}>
        <div>
            <span className='xl:text-2xl lg:text-xl'>
                <FontAwesomeIcon className="mr-4" icon={`fa-solid ${faIconString}`} />
            </span>
            <span className='sm:text-lg xl:text-xl'>{getRoleString(role)}</span>
        </div>
        <div className='xl:text-2xl lg:text-xl'><FontAwesomeIcon icon="fa-solid fa-circle-arrow-right" /></div>
    </button>)
}