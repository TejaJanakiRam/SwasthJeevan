import { useState, useEffect } from 'react'
import Navbar from './Navbar/Navbar.js';
import Specialities from './Specialities-Section/Specialities.js';
import FAQ from './FAQ-Section/FAQ.js';
import BookNow from './BookNow-Section/BookNow.js';
import axios from "axios";
import Footer from './Footer/Footer.js';

const user = {
  name: 'Tom Cook',
  email: 'tom@example.com',
  imageUrl:
    'https://th.bing.com/th/id/OIP.tvaMwK3QuFxhTYg4PSNNVAHaHa?w=217&h=218&c=7&r=0&o=5&dpr=1.3&pid=1.7'
}
const navigation = [
  { name: 'Home', href: '#', current: true },
  { name: 'Appointments', href: '#', current: false },
  { name: 'EHR', href: '#', current: false },
]
const userNavigation = [
  { name: 'Your Profile', href: '#' },
  { name: 'Sign out', href: '/' },
]

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
const specialities = [
  {
    id: 1,
    name: 'Pulmonology',
    imageUrl: '../../img/pulmonology.jpeg'
  },
  {
    id: 2,
    name: 'Gastroentrology',
    imageUrl: '../../img/gastroentrology.jpeg'
  },
  {
    id: 3,
    name: 'Cardiology',
    imageUrl: '../../img/cardiology.jpeg'
  },
  {
    id: 4,
    name: 'Opthalmology',
    imageUrl: '../../img/opthalmology.jpeg'
  },
  {
    id: 5,
    name: 'Orthopedics',
    imageUrl: '../../img/orthopedics.jpeg'
  },
  {
    id: 6,
    name: 'Urology',
    imageUrl: '../../img/urology.jpeg'
  },
  {
    id: 7,
    name: 'Hepatology',
    imageUrl: '../../img/hepatology.jpeg'
  },
  {
    id: 8,
    name: 'Rhinology',
    imageUrl: '../../img/rhinology.jpeg'
  },
  {
    id: 9,
    name: 'Otology',
    imageUrl: '../../img/otology.jpeg'
  },
  {
    id: 10,
    name: 'Neurology',
    imageUrl: '../../img/neurology.jpeg'
  }
]

function classNames(...classes) {
  return classes.filter(Boolean).join(' ')
}

export default function Dashboard(props) {
  const [username, setUsername] = useState('');

  const getDetails = async () => {
    try {
      const response = await axios.get('http://localhost:4000/api/patient/profile', {
        headers: {
          Authorization: `Bearer ${props.token}` // Assuming props.token contains the JWT token
        }
      });
      setUsername(response.data.username)
    } catch (error) {
      console.error("Failed to fetch user details:", error);
    }
  };
  useEffect(() => {
    getDetails();
  }, []);

  return (
    <div>
      <Navbar onLogout={props.onLogout} />
      <BookNow username={username}/>
      <Specialities username={username} data={specialities} />
      <FAQ username={username} data={faqs} />
      <Footer />
    </div>
  )



}