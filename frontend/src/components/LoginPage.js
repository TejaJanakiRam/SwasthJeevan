import React, { useState } from 'react';
import axios from "axios";

function LoginPage() {
    const [role, setRole] = useState(null);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleRoleSelection = (selectedRole) => {
        setRole(selectedRole);
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            console.log(role.toUpperCase());
            const response = await axios.post('http://localhost:4000/auth/signin', {
                username,
                password,
                role: role.toUpperCase()
            });
            console.log(response.data);
        } catch (error) {
            console.error('Login failed', error);
        }
    };

    const handleForgotPassword = () => {
        console.log('Forgot password');
    };

    const handleSignUp = () => {
        console.log('Sign up');
    };

    return (
        <div className="flex h-screen ">
            <div className="flex-1 flex items-center justify-center bg-blue-100">
                <div className="flex flex-col items-center">
                    <h2 className="text-2xl text-blue-800 mb-4">Sign In As</h2>
                    <div className="flex flex-col space-y-5">
                        <button type="button" className="px-6 py-3.5 w-80 text-2xl text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" onClick={() => handleRoleSelection('patient')}>Patient</button>
                        <button type="button" className="px-6 py-3.5 w-80 text-2xl text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" onClick={() => handleRoleSelection('doctor')}>Doctor</button>
                        <button type="button" className="px-6 py-3.5 w-80 text-2xl text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" onClick={() => handleRoleSelection('hospital-admin')}>Hospital Admin</button>
                        <button type="button" className="px-6 py-3.5 w-80 text-2xl text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" onClick={() => handleRoleSelection('system-admin')}>System Admin</button>
                    </div>
                </div>
            </div>
            <div className="lg:p-36 md:p-52 sm:20 p-8 w-full lg:w-1/2 flex flex-col justify-center items-center">
                <h1 className="text-2xl font-semibold mb-4">Login</h1>
                <form onSubmit={handleLogin}>
                    <div className="mb-4">
                        <label htmlFor="username" className="block text-gray-600">Username</label>
                        <input type="text" id="username" name="username" className="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autoComplete="off" onChange={(e) => setUsername(e.target.value)} />
                    </div>
                    <div className="mb-4">
                        <label htmlFor="password" className="block text-gray-600">Password</label>
                        <input type="password" id="password" name="password" className="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autoComplete="off" onChange={(e) => setPassword(e.target.value)} />
                    </div>
                    <div className="mb-6 text-blue-500">
                        <a href="#" className="hover:underline" onClick={handleForgotPassword}>Forgot Password?</a>
                    </div>
                    <button type="submit" className="bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-md py-2 px-4 w-full">Login</button>
                </form>
                {role === 'patient' && (
                    <div className="mt-6 text-blue-500 text-center">
                        <a href="#" className="hover:underline" onClick={handleSignUp}>Sign up Here</a>
                    </div>
                )}
            </div>
        </div>
    );
}

export default LoginPage;
