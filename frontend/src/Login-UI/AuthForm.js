import React, { useState } from 'react';
import InputField from './InputField.js';


export default function AuthForm(props) {

    return (<form onSubmit={props.handleSubmit} className='flex flex-col items-center w-full'>
        <InputField fieldName={"username"} displayFieldName={"Username"} setFunction={props.setUsername} />
        <InputField fieldName={"password"} displayFieldName={"Password"} setFunction={props.setPassword} />
        {props.formType === "signup" && <InputField fieldName={"confirmPassword"} displayFieldName={"Confirm Password"} setFunction={props.setConfirmPassword} />}
        {<div className="text-red-500 mb-4">{props.formType === "signup" && props.signupError ? props.signupError : " "}</div>}
        {props.formType === "login" && <div className='self-end'>
            <a href="#" className="hover:underline text-blue-500 pr-8 font-semibold" onClick={props.handleForgotPassword}>Forgot Password?</a>
        </div>}
        <button type="submit" className=" border-2 text-lg border-blue-500 font-semibold text-white bg-blue-500 rounded-xl my-6 p-2 w-1/2 hover:bg-blue-600 hover:border-blue-600 transition-all duration-300">{props.formType === "login" ? "Login" : "Signup"}</button>
        {props.role === 'patient' && (!props.showSignup ? (
            <div className="self-start text-blue-500 text-lg pl-6">
                New User? <a href="#" className="hover:underline font-semibold " onClick={props.showSignupTrigger}>Sign up Here</a>
            </div>
        ) : (<div className="self-start text-blue-500 text-lg pl-6">
            <a href="#" onClick={props.showSignupTrigger} className="hover:underline font-semibold">Back to Sign In</a>
        </div>))}

    </form>)
}