import React, { useState } from 'react';
import axios from "axios";
import AuthForm from './AuthForm.js';

function SignupPage(props) {
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
            const response = await axios.post('http://localhost:4000/auth/signup', {
                username,
                password,
                role: props.role.toUpperCase()
            });
            console.log(response.data);
            props.showSignupTrigger();
        } catch (error) {
            console.log(error);
            setSignupError("Signup failed. Please try again.");
        }
    };
    const authFormProps = {
        ...props,
        formType: "signup",
        setUsername: setUsername,
        setPassword: setPassword,
        setConfirmPassword: setConfirmPassword,
        signupError: signupError,
        handleSubmit: handleSignUp
    };


    return (
        <AuthForm {...authFormProps} />
        // <form onSubmit={handleSignUp} className='flex flex-col items-center space-y-5 w-full'>
        //     <InputField fieldName={"username"} displayFieldName={"Username"} setFunction={setUsername} />
        //     <InputField fieldName={"password"} displayFieldName={"Password"} setFunction={setPassword} />
        //     <InputField fieldName={"confirmPassword"} displayFieldName={"Confirm Password"} setFunction={setConfirmPassword} />
        //     {signupError && <div className="text-red-500 mb-4">{signupError}</div>}
        //     <button type="submit" className=" border-2 sm:text-lg xl:text-xl border-blue-500 text-blue-500 font-bold rounded-xl p-1 xl:p-2 w-1/2 hover:bg-blue-500 hover:text-white transition-all duration-300">"Sign Up"</button>
        //     <div className="mt-4 text-blue-500 text-center">
        //         <a href="#" onClick={showSignUpTrigger} className="hover:underline">Back to Sign In</a>
        //     </div>
        // </form>
    );
}

export default SignupPage;
