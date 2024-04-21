import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from "react-router-dom";

const AppointmentList = ({ doctorProfile, token }) => {
  const [isLoading, setIsLoading] = useState(false);
  const [isPatientAvailable, setIsPatientAvailable] = useState(
    localStorage.getItem(`isPatientAvailable_${doctorProfile.id}`) === 'true'
  );

  const handleMarkAvailable = async () => {
    try {
      setIsLoading(true);
      const response = await axios.get('http://localhost:4000/api/queue/assign_patient', {
        params: {
          spec_code: doctorProfile.speciality.specialityCode,
          doctor_id: doctorProfile.id
        },
        headers: {
          Authorization: `Bearer ${token}`
        }
      });

      if (response.data) {
        setIsPatientAvailable(true);
        localStorage.setItem(`isPatientAvailable_${doctorProfile.id}`, 'true');
      } else {
        setIsPatientAvailable(false);
        localStorage.setItem(`isPatientAvailable_${doctorProfile.id}`, 'false');
      }
    } catch (error) {
      console.error('Failed to assign patient to doctor:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleResetAvailability = () => {
    setIsPatientAvailable(false);
    localStorage.setItem(`isPatientAvailable_${doctorProfile.id}`, 'false');
  };

  useEffect(() => {
    const intervalId = setInterval(() => {
      if (!isPatientAvailable) {
        handleMarkAvailable();
      }
    }, 2000);

    return () => clearInterval(intervalId);
  }, [doctorProfile, token, isPatientAvailable]);

  return (
    <div className="min-h-screen flex flex-col items-center bg-gradient-to-r from-blue-100 to-blue-500">
      <div className="absolute top-20 left-9 mt-4 ml-4">
        <button
          className={`py-2 px-4 rounded ${
            isPatientAvailable ? 'bg-green-500' : 'bg-red-500'
          } hover:bg-green-700 text-white font-bold border border-transparent shadow ${
            isLoading ? 'cursor-not-allowed opacity-50' : ''
          }`}
          disabled={isLoading}
        >
          {isLoading
            ? 'Processing...'
            : isPatientAvailable
            ? 'Patient Available'
            : 'No Patient Available'}
        </button>
      </div>
      <div>
            <Link to="/videocall">
          <button 
            type="submit" 
            className={`font-semibold border-2 border-blue-400 bg-blue-400 text-white rounded-xl px-10 py-2 my-8 hover:bg-blue-500 hover:border-blue-500 transition-all duration-300 shadow shadow-slate-900 ${!isPatientAvailable && 'cursor-not-allowed opacity-50'}`} 
            disabled={!isPatientAvailable}
          >
            Start Consultation
          </button>
        </Link>
      </div>

      <div>
        <button onClick={handleResetAvailability} className="font-semibold border-2 border-red-400 bg-red-400 text-white rounded-xl px-10 py-2 my-8 hover:bg-red-500 hover:border-red-500 transition-all duration-300 shadow shadow-slate-900">End Consultation</button>
      </div>
    </div>
  );
};

export default AppointmentList;
