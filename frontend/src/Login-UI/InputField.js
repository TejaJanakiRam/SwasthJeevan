import React, { useState } from 'react';

export default function InputField({ fieldName, displayFieldName, setFunction }) {
    let type = "text";
    if(fieldName.toLowerCase().includes("password")){
        type = "password";
    }
    return (<div className="w-full">
        <label htmlFor={fieldName} className="block sm:text-lg xl:text-xl py-1 font-semibold">{displayFieldName}</label>
        <input type={type} id={fieldName} name={fieldName} placeholder={displayFieldName} className="w-full border-2 border-blue-500 rounded-xl py-2 px-3 focus:outline-none  focus:border-blue-800 sm:text-sm xl:text-lg text-blue-800" autoComplete="off" onChange={(e) => setFunction(e.target.value)} />
    </div>)
}