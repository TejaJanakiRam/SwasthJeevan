import Sidebar from "../Common-UI/Sidebar/Sidebar";
import InputField from "../Login-UI/InputField";
import { useEffect, useState } from "react";
import axios from "axios";
import DropdownSelect from "./DropdownSelect";

export default function RegisterOrganization(props) {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [registration_num, setRegistrationNum] = useState('');
    const [address, setAddress] = useState('');
    const [signupError, setSignupError] = useState('');
    const [signedUp, setSignedUp] = useState(false);

    const handleSignUp = async (e) => {
        e.preventDefault();
        try {
            const body = {
                name: name,
                email: email,
                phone: phone,
                registration_num: registration_num,
                address: address,
            }
            console.log(body);
            const response = await axios.post('http://localhost:4000/api/sys_admin/register_organization', body, {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            console.log(response.data);
            setSignedUp(true);

        } catch (error) {
            console.log(error);
            setSignupError("Signup failed. Please try again.");
        }
    };

    const reset = () => {
        setSignedUp(false);
        setName('');
        setEmail('');
        setPhone('');
        setRegistrationNum('');
        setAddress('');
        setSignupError('');
    }


    return (<div>
        <Sidebar role={"sys_admin"} onLogout={props.onLogout} />
        <div className="bg-blue-400 h-screen flex justify-center items-center">
            {!signedUp ?
                <form onSubmit={handleSignUp} className='flex flex-col items-center justify-center p-4 bg-white w-7/12 h-1/2 rounded-2xl shadow-lg shadow-slate-800 text-blue-500'>
                    <div className='w-full flex justify-between'>
                        <InputField type={"text"} fieldName={"name"} displayFieldName={"Name"} setFunction={setName} />
                        <InputField type={"text"} fieldName={"registration_num"} displayFieldName={"Registration Number"} setFunction={setRegistrationNum} />
                    </div>
                    <div className='w-full flex justify-between'>
                        <InputField type={"email"} fieldName={"email"} displayFieldName={"Email"} setFunction={setEmail} />
                        <InputField type={"number"} fieldName={"phone"} displayFieldName={"Phone"} setFunction={setPhone} />
                    </div>
                    <div className='w-full flex justify-between'>
                        <InputField type={"text"} fieldName={"address"} displayFieldName={"Address"} setFunction={setAddress} />
                    </div>


                    {<div className="text-red-500 mb-4">{signupError ? signupError : " "}</div>}

                    <button type="submit" className=" border-2 text-lg border-blue-500 font-semibold text-white bg-blue-500 rounded-xl my-6 p-2 w-1/2 hover:bg-blue-600 hover:border-blue-600 transition-all duration-300 shadow shadow-slate-800">Register Organization</button>
                </form> :
                <div className='flex flex-col items-center justify-around p-4 bg-white w-4/6 h-4/6 rounded-2xl shadow-lg shadow-slate-800'>
                    <h1 className="text-4xl tracking-wide text-green-500 font-bold">{'Organization Registration Successful!'.toUpperCase()}</h1>
                    <div className="flex justify-end w-full">
                        <a onClick={() => { reset() }} className="hover:cursor-pointer text-2xl bg-blue-500 px-8 py-2 text-white rounded-xl">Back</a>
                    </div>
                </div>

            }
        </div>
    </div>)
}