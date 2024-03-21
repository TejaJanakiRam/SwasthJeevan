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
    );
}

export default SignupPage;
