import React, { useState } from 'react';
import axios from "axios";
function LoginPage() {
    const [role, setRole] = useState(null);

    const handleRoleSelection = (selectedRole) => {
        setRole(selectedRole);
    };


    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (e) => {
        // Handle login logic here
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/users/login', {
                username,
                password
            });
            console.log(response.data); 
        } catch (error) {
            console.error('Login failed', error);
        }

    };

    const handleForgotPassword = () => {
        // Handle forgot password logic here
        console.log('Forgot password');
    };

    const handleSignUp = () => {
        // Handle sign up logic here
        console.log('Sign up');
    };

    return (
        <div className="flex h-screen ">
            <div className="flex-1 flex items-center justify-center bg-blue-100">
                <div className="flex flex-col items-center">
                    <h2 className="text-2xl text-blue-800 mb-4">Sign In As</h2>
                    <div className="flex flex-col space-y-5">

                        <button type="button" class="px-6 py-3.5 w-80 text-2xl text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" onClick={() => handleRoleSelection('patient')}>Patient</button>

                        <button type="button" class="px-6 py-3.5 w-80 text-2xl text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" onClick={() => handleRoleSelection('doctor')}>Doctor</button>
                        <button type="button" class="px-6 py-3.5 w-80 text-2xl text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" onClick={() => handleRoleSelection('hospital-admin')}>Hospital Admin</button>
                        <button type="button" class="px-6 py-3.5  w-80 text-2xl text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" onClick={() => handleRoleSelection('system-admin')}>System Admin</button>
                    </div>
                </div>
            </div>
            <div class="lg:p-36 md:p-52 sm:20 p-8 w-full lg:w-1/2 flex flex-col justify-center items-center">
                <h1 class="text-2xl font-semibold mb-4">Login</h1>
                <form onSubmit={handleLogin}>
                    <div class="mb-4">
                        <label for="username" class="block text-gray-600">Username</label>
                        <input type="text" id="username" name="username" class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autocomplete="off" />
                    </div>

                    <div class="mb-4">
                        <label for="password" class="block text-gray-600">Password</label>
                        <input type="password" id="password" name="password" class="w-full border border-gray-300 rounded-md py-2 px-3 focus:outline-none focus:border-blue-500" autocomplete="off" />
                    </div>



                    <div class="mb-6 text-blue-500">
                        <a href="#" class="hover:underline">Forgot Password?</a>
                    </div>

                    <button type="submit" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold rounded-md py-2 px-4 w-full">Login</button>
                </form>

                {role === 'patient' && (
                    <div class="mt-6 text-blue-500 text-center">
                        <a href="#" class="hover:underline">Sign up Here</a>
                    </div>
                )}

            </div>
        </div>

    );
}

export default LoginPage;
