import React, { useState } from 'react';
import InputField from './InputField.js';


export default function AuthForm(props) {

    return (<form onSubmit={props.handleSubmit} className='flex flex-col items-center space-y-5 w-full'>
        <InputField fieldName={"username"} displayFieldName={"Username"} setFunction={props.setUsername} />
        <InputField fieldName={"password"} displayFieldName={"Password"} setFunction={props.setPassword} />
        {props.formType === "signup" && <InputField fieldName={"confirmPassword"} displayFieldName={"Confirm Password"} setFunction={props.setConfirmPassword} />}
        {<div className="text-red-500 mb-4">{props.formType === "signup" && props.signupError ? props.signupError : " "}</div>}
        {props.formType === "login" && <div className='self-end'>
            <a href="#" className="underline text-blue-500 sm:text-sm xl:text-lg" onClick={props.handleForgotPassword}>Forgot Password?</a>
        </div>}
        <button type="submit" className=" border-2 sm:text-lg xl:text-xl border-blue-500 text-blue-500 font-bold rounded-xl p-1 xl:p-2 w-1/2 hover:bg-blue-500 hover:text-white transition-all duration-300">{props.formType === "login" ? "Login" : "Signup"}</button>
        {props.role === 'patient' && (!props.showSignup ? (
            <div className="self-start text-blue-500 sm:text-sm xl:text-lg">
                New User? <a href="#" className="underline " onClick={props.showSignupTrigger}>Sign up Here</a>
            </div>
        ) : (<div className="self-start text-blue-500 sm:text-sm xl:text-lg">
            <a href="#" onClick={props.showSignupTrigger} className="underline">Back to Sign In</a>
        </div>))}

    </form>)
}