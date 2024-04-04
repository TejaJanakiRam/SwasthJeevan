import React, { useState } from 'react';
import axios from "axios";
import InputField from './InputField.js';



export default function LoginPage(props) {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');


    const handleForgotPassword = () => {
        console.log('Forgot password');
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const body = {
                username: username,
                password: password,
                type: props.role.toUpperCase()
            }
            const response = await axios.post('http://localhost:4000/auth/signin', body);
            const token = String(response.data.jwt);
            props.onLogin(token, props.role);
        } catch (error) {
            alert("Wrong credentials!")
        }
    };


    return (
        <form onSubmit={handleLogin} className='flex flex-col items-center w-full p-4'>
            <InputField type={"text"} fieldName={"username"} displayFieldName={"Username"} setFunction={setUsername} />
            <InputField type={"password"} fieldName={"password"} displayFieldName={"Password"} setFunction={setPassword} />

            <div className='self-end'>
                <a href="#" className="hover:underline text-blue-500 pr-8 font-semibold" onClick={handleForgotPassword}>Forgot Password?</a>
            </div>
            <button type="submit" className=" border-2 text-lg border-blue-500 font-semibold text-white bg-blue-500 rounded-xl my-6 p-2 w-1/2 hover:bg-blue-600 hover:border-blue-600 transition-all duration-300 shadow shadow-slate-800">Login</button>
            {props.role === 'patient' &&
                <div className="self-start text-blue-500 pl-6">
                    New User? <a href="#" className="hover:underline font-semibold " onClick={props.showSignupTrigger}>Sign up Here</a>
                </div>
            }

        </form>
    );
}


