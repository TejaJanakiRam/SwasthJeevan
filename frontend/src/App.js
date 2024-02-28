// import React from "react";
// import LoginPage from './components/LoginPage.js';
// import Dashboard from './Patinet_UI/Dashboard.js';

// function App() {
  

//   return (
//     <div className="App">
//       <LoginPage /> 
//       {/* <Dashboard/> */}
//     </div>
//   );
// }

// export default App;
import React, { useState } from "react";
import { BrowserRouter , Routes, Route, Navigate } from "react-router-dom";
import LoginPage from './components/LoginPage.js';
import Dashboard from './Patinet_UI/Dashboard.js';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [token, setToken] = useState('');

  const handleLogin = (jwtToken) => {
    setToken(jwtToken);
    setIsLoggedIn(true);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
  };
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={isLoggedIn ? <Navigate to="/dashboard" /> : <LoginPage onLogin={handleLogin} />}/>
          <Route path="/dashboard" element={isLoggedIn ? <Dashboard token={token} onLogout={handleLogout} /> : <Navigate to="/" />} />       
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
