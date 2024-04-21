import { useState, useEffect } from 'react'
import axios from "axios";
import Navbar from '../Common-UI/Navbar/Navbar.js';
import Specialities from './Specialities-Section/Specialities.js';
import FAQ from './FAQ-Section/FAQ.js';
import BookNow from './BookNow-Section/BookNow.js';
import Footer from './Footer/Footer.js';



const faqs = [
  {
    id: 1,
    question: "What is online consultation",
    answer: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
  },
  {
    id: 2,
    question: "Which doctor should I consult and how to book appointment with specific doctor?",
    answer: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
  },
  {
    id: 3,
    question: "What are the timings?",
    answer: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
  },
  {
    id: 4,
    question: "Can I get a prescription",
    answer: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
  },

]


function classNames(...classes) {
  return classes.filter(Boolean).join(' ')
}

export default function PatientDashboard(props) {

  const [user, setUser] = useState(null);
  const getDetails = async () => {
    try {
      const response = await axios.get('http://localhost:4000/api/patient/profile', {
        headers: {
          Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
        }
      });
      setUser((response.data))
    } catch (error) {
      console.error("Failed to fetch user details:", error);
    }
  };

  useEffect(() => {
    getDetails();
  }, []);
  
  return (
    <div>
      <Navbar role={"patient"} onLogout={props.onLogout} />
      {user && <BookNow user={user} />}
      {user && <Specialities token={props.token} />}
      {user && <FAQ data={faqs} />}
      {user && <Footer />}
    </div>
  )



}