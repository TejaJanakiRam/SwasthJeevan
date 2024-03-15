import React, { useState } from 'react';
import axios from "axios";
import AuthForm from './AuthForm.js';



export default function LoginPage(props) {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');


    const handleForgotPassword = () => {
        console.log('Forgot password');
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        try {

            const response = await axios.post('http://localhost:4000/auth/signin', {
                username,
                password,
                role: props.role.toUpperCase()
            });
            console.log(response.data.jwt);
            const token = String(response.data.jwt);
            props.onLogin(token, props.role.toUpperCase());
        } catch (error) {
            alert("Wrong credentials!")
        }

    };
    const authFormProps = {
        ...props, 
        formType: "login",
        setUsername: setUsername,
        setPassword: setPassword,
        handleForgotPassword: handleForgotPassword,
        handleSubmit: handleLogin
    };
    

    return (
        <AuthForm {...authFormProps} />
    );
}


