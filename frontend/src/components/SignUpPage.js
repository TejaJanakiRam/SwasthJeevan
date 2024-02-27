import React, { useState } from 'react';
import axios from "axios";

function SignupPage({ role, showSignUpTrigger }) {
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
                role: role.toUpperCase()
            });
            console.log(response.data);
            showSignUpTrigger();
        } catch (error) {
            console.log(error);
            setSignupError("Signup failed. Please try again.");
        }
    };

    return (
        <form onSubmit={handleSignUp}>
            <div className="mb-4">
                <label htmlFor="username" className="block text-gray-600">Username</label>
                <input type="text" id="username" name="username" className="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autoComplete="off" onChange={(e) => setUsername(e.target.value)} />
            </div>
            <div className="mb-4">
                <label htmlFor="password" className="block text-gray-600">Password</label>
                <input type="password" id="password" name="password" className="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autoComplete="off" onChange={(e) => setPassword(e.target.value)} />
            </div>
            <div className="mb-4">
                <label htmlFor="confirmPassword" className="block text-gray-600">Confirm Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" className="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autoComplete="off" onChange={(e) => setConfirmPassword(e.target.value)} />
            </div>
            {signupError && <div className="text-red-500 mb-4">{signupError}</div>}
            <button type="submit" className="bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-md py-2 px-4 w-full">Sign Up</button>
            <div className="mt-4 text-blue-500 text-center">
                <a href="#" onClick={showSignUpTrigger} className="hover:underline">Back to Sign In</a>
            </div>
        </form>
    );
}

export default SignupPage;
