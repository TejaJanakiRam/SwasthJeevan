import Sidebar from "../Common-UI/Sidebar/Sidebar";
import InputField from "../Login-UI/InputField";
import { useEffect, useState } from "react";
import axios from "axios";
import DropdownSelect from "./DropdownSelect";
export default function DoctorSignUp(props) {

    const [user, setUser] = useState(null);
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [gender, setGender] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [tempPassword, setTempPassword] = useState('');
    const [registration_num, setRegistrationNum] = useState('');
    const [org_registration_num, setOrgRegistrationNum] = useState('');
    const [speciality, setSpeciality] = useState('');
    const [signupError, setSignupError] = useState('');
    const [specialitiesList, setSpecialitiesList] = useState(null);
    const [signedUp, setSignedUp] = useState(false);

    const getDetails = async () => {
        try {
            const response = await axios.get('http://localhost:4000/api/org_admin/profile', {
                headers: {
                    Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
                }
            });
            setUser((response.data));
            setOrgRegistrationNum(String(response.data.organization.registrationNum))
        } catch (error) {
            console.error("Failed to fetch user details:", error);
        }
    };


    const getAllSpecialties = async () => {
        const response = await axios.get('http://localhost:4000/api/specialities', {
            headers: {
                Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
            }
        });
        setSpecialitiesList(response.data);

    }

    useEffect(() => {
        getAllSpecialties();
        getDetails();
    }, []);

    const role = "doctor";

    const gendersList = [
        { id: 1, name: "Male" },
        { id: 2, name: "Female" },
        { id: 3, name: "Other" },
    ]

    const handleSignUp = async (e) => {
        e.preventDefault();
        try {
            const spec_code = specialitiesList.find(spec => spec.name === speciality).specialityCode;
            const body = {
                name: (firstName + ' ' + lastName),
                username: username,
                password: password,
                email: email,
                phone: phone,
                gender: gender.toUpperCase(),
                registration_num: registration_num,
                speciality_code: spec_code,
                org_registration_num: org_registration_num,
                type: role.toUpperCase(),
            }
            console.log(body);
            const response = await axios.post('http://localhost:4000/auth/signup', body);
            setUsername(String(response.data.username));
            setTempPassword(String(response.data.password));
            setSignedUp(true);

        } catch (error) {
            console.log(error);
            setSignupError("Signup failed. Please try again.");
        }
    };


    const reset = () => {
        setSignedUp(false);
        setFirstName('');
        setLastName('');
        setEmail('');
        setPhone('');
        setGender('');
        setUsername('');
        setPassword('');
        setTempPassword('');
        setRegistrationNum('');
        setSpeciality('');
        setSignupError('');
    }


    return (<div>
        <Sidebar role={"org_admin"} onLogout={props.onLogout} />
        <div className="bg-blue-400 h-screen flex justify-center items-center">
            {!signedUp ?
                <form onSubmit={handleSignUp} className='flex flex-col items-center justify-center p-4 bg-white w-7/12 h-1/2 rounded-2xl shadow-lg shadow-slate-800 text-blue-500'>
                    <div className='w-full flex justify-between'>
                        <InputField type={"text"} fieldName={"firstname"} displayFieldName={"First Name"} setFunction={setFirstName} />
                        <InputField type={"text"} fieldName={"lastname"} displayFieldName={"Last Name"} setFunction={setLastName} />
                    </div>
                    <div className='w-full flex justify-between'>
                        <InputField type={"email"} fieldName={"email"} displayFieldName={"Email"} setFunction={setEmail} />
                        <InputField type={"number"} fieldName={"phone"} displayFieldName={"Phone"} setFunction={setPhone} />
                    </div>
                    <div className="w-full flex">

                        <div className="w-1/2 flex justify-between">
                            <div className="w-7/12">
                                {specialitiesList && <DropdownSelect data={specialitiesList} displayFieldName={"Speciality"} selectedItem={speciality} setSelectedItem={setSpeciality} />}
                            </div>
                            <div className="w-5/12">
                                {gendersList && <DropdownSelect data={gendersList} displayFieldName={"Gender"} selectedItem={gender} setSelectedItem={setGender} />}
                            </div>
                        </div>
                        <div className="w-1/2 flex">
                            <InputField type={"text"} fieldName={"registration_num"} displayFieldName={"Registration Number"} setFunction={setRegistrationNum} />
                        </div>
                    </div>


                    {<div className="text-red-500 mb-4">{signupError ? signupError : " "}</div>}

                    <button type="submit" className=" border-2 text-lg border-blue-500 font-semibold text-white bg-blue-500 rounded-xl my-6 p-2 w-1/2 hover:bg-blue-600 hover:border-blue-600 transition-all duration-300 shadow shadow-slate-800">Add Doctor</button>
                </form> :
                <div className='flex flex-col items-center justify-around p-4 bg-white w-4/6 h-4/6 rounded-2xl shadow-lg shadow-slate-800'>
                    <h1 className="text-5xl tracking-wide text-green-500 font-bold">{'Doctor Registration Successful!'.toUpperCase()}</h1>
                    <div className="text-3xl text-blue-500 border-2 rounded-xl border-blue-500">
                        <p className="p-4">Username: {username}</p>
                        <p className="p-4">Password: {tempPassword}</p>
                    </div>
                    <div className="flex justify-end w-full">
                        <a onClick={() => { reset() }} className="hover:cursor-pointer text-2xl bg-blue-500 px-8 py-2 text-white rounded-xl">Back</a>
                    </div>
                </div>

            }
        </div>
    </div>)
}