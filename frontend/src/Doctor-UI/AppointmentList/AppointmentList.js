import React, { useState } from 'react';
import axios from 'axios';

const AppointmentList = ({ doctorProfile, token }) => {
  const [isMarkedAvailable, setIsMarkedAvailable] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const handleMarkAvailable = async () => {
    try {
      setIsLoading(true);
      const response = await axios.get('http://localhost:4000/api/queue/add_doc', {
        params: {
          spec_code: doctorProfile.speciality.specialityCode,
          doctor_id: doctorProfile.id
        },
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
      // Handle success response here if needed
      console.log(response.data);
      setIsMarkedAvailable(true);
    } catch (error) {
      // Handle error here
      console.error('Failed to mark doctor as available:', error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleassignPatient = async () => {
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
      // Handle success response here if needed
      console.log(response.data);
      setIsMarkedAvailable(true);
    } catch (error) {
      // Handle error here
      console.error('Failed to mark doctor as available:', error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex flex-col items-center bg-gradient-to-r from-blue-100 to-blue-500">
    <div className="absolute top-20 left-9 mt-4 ml-4">
        <button
        onClick={handleMarkAvailable}
        className={`py-2 px-4 rounded ${
            isMarkedAvailable
            ? 'bg-red-500 hover:bg-red-700'
            : 'bg-green-500 hover:bg-green-700'
        } text-white font-bold border border-transparent shadow ${
            isLoading ? 'cursor-not-allowed opacity-50' : ''
        }`}
        disabled={isMarkedAvailable || isLoading}
        >
        {isLoading ? 'Processing...' : (isMarkedAvailable ? 'Already Marked Available' : 'Mark as Available')}
        </button>
    </div>
  </div>
  );
};

export default AppointmentList;


