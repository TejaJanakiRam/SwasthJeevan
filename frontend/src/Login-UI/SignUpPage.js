import React, { useState } from 'react';
import axios from "axios";
import InputField from './InputField.js';

function SignupPage(props) {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [gender, setGender] = useState('');
    const [dob, setDob] = useState('');
    const [address, setAddress] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [signupError, setSignupError] = useState('');
    const handleSignUp = async (e) => {
        e.preventDefault();
        if (password !== confirmPassword) {
            setSignupError("Passwords do not match");
            return;
        }
        try {
            const body = {
                name: (firstName + ' ' + lastName),
                username: username,
                password: password,
                email: email,
                phone: phone,
                gender: gender.toUpperCase(),
                dob: dob,
                type: props.role.toUpperCase(),
                address: address
            }
            console.log(body);
            const response = await axios.post('http://localhost:4000/auth/signup', body);
            const token = String(response.data.jwt);
            props.onLogin(token, props.role);
            props.showSignupTrigger();
        } catch (error) {
            console.log(error);
            setSignupError("Signup failed. Please try again.");
        }
    };



    return (
        <form onSubmit={handleSignUp} className='flex flex-col items-center w-full p-4'>
            <div className='w-full flex justify-between'>
                <InputField type={"text"} fieldName={"firstname"} displayFieldName={"First Name"} setFunction={setFirstName} />
                <InputField type={"text"} fieldName={"lastname"} displayFieldName={"Last Name"} setFunction={setLastName} />

            </div>
            <div className='w-full flex justify-between'>
                <InputField type={"email"} fieldName={"email"} displayFieldName={"Email"} setFunction={setEmail} />
                <InputField type={"number"} fieldName={"phone"} displayFieldName={"Phone"} setFunction={setPhone} />
            </div>
            <div className='w-full flex justify-between'>
                <div className='w-1/2 flex justify-between'>
                    <div className="my-2 pl-4">
                        <label htmlFor="dob" className="block text-lg font-semibold">Date of Birth</label>
                        <input type="date" id="dob" name="dob" className="w-full text-blue-500 border-2 border-blue-500 rounded-xl py-2 px-3 focus:outline-none  focus:border-blue-800 focus:text-blue-800 sm:text-sm lg:text-lg" autoComplete="off" onChange={(e) => setDob(e.target.value)} />
                    </div>
                    <div className="my-2 pr-4">
                        <label for="gender" className="block text-lg font-semibold">Gender</label>
                        <select defaultValue="other" name="gender" id="gender" className="w-full text-blue-500 border-2 border-blue-500 rounded-xl py-3 px-3 focus:outline-none  focus:border-blue-800 focus:text-blue-800 sm:text-sm lg:text-lg" onChange={(e) => { setGender(e.target.value) }}>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                        </select>
                    </div>
                </div>
                <div className="my-2 pl-4 w-1/2">
                    <label htmlFor="dob" className="block text-lg font-semibold">Username</label>
                    <input type="text" id="username" name="username" className="w-full text-blue-500 border-2 border-blue-500 rounded-xl py-2 px-3 focus:outline-none  focus:border-blue-800 focus:text-blue-800 sm:text-sm lg:text-lg" autoComplete="off" onChange={(e) => setUsername(e.target.value)} />
                </div>
            </div>
            <div className='w-full flex justify-between'>
                <InputField type={"password"} fieldName={"password"} displayFieldName={"Password"} setFunction={setPassword} />
                <InputField type={"password"} fieldName={"confirmPassword"} displayFieldName={"Confirm Password"} setFunction={setConfirmPassword} />
            </div>
            <InputField type={"text"} fieldName={"address"} displayFieldName={"Address"} setFunction={setAddress} />

            {<div className="text-red-500 mb-4">{signupError ? signupError : " "}</div>}

            <button type="submit" className=" border-2 text-lg border-blue-500 font-semibold text-white bg-blue-500 rounded-xl my-6 p-2 w-1/2 hover:bg-blue-600 hover:border-blue-600 transition-all duration-300 shadow shadow-slate-800">Signup</button>
            {props.role === 'patient' &&
                <div className="self-start text-blue-500 pl-6">
                    Already a User? <a href="#" onClick={props.showSignupTrigger} className="hover:underline font-semibold">Sign In Here</a>
                </div>
            }

        </form>
    );
}

export default SignupPage;
