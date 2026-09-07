import "./App.css";
import { Route, Routes, Link } from "react-router-dom";
import Home from "./Components/Home";
import About from "./Components/About";
import AccountManagement from "./Components/AccountManagement";
import DepartmentManagement from "./Components/DepartmentManagement";
import Login from "./Components/Login";
import SignUp from "./Components/SignUp";
import Error from "./Components/Error";

function App() {
  return (
    <div className="container">
      <div className="row">
        {/* Menu */}
        <nav
          className="navbar navbar-inverse"
          style={{ backgroundColor: "black", color: "black" }}
        >
          <div className="container-fluid">
            <ul className="nav navbar-nav">
              <li>
                <Link to="/">VTI ACADEMY</Link>
              </li>
              <li>
                <Link to="/home">Home</Link>
              </li>
              <li>
                <Link to="/about">About</Link>
              </li>
              <li>
                <Link to="/AccountManagement">Account Management</Link>
              </li>
              <li>
                <Link to="/DepartmentManagement">Department Management</Link>
              </li>
            </ul>
            <ul className="nav navbar-nav navbar-right">
              <li>
                <Link to="/Sign_Up">
                  <span className="glyphicon glyphicon-user"></span> Sign Up
                </Link>
              </li>
              <li>
                <Link to="/Login">
                  <span className="glyphicon glyphicon-log-in"></span> Login
                </Link>
              </li>
            </ul>
          </div>
        </nav>
      </div>

      {/* Content */}
      <Routes>
        <Route path="/" element={<Home />} exact />
        <Route path="/home" element={<Home />} exact />
        <Route path="/about" element={<About />} exact />
        <Route path="/AccountManagement" element={<AccountManagement />} />
        <Route
          path="/DepartmentManagement"
          element={<DepartmentManagement />}
        />
        <Route path="/Login" element={<Login />} />
        <Route path="/Sign_Up" element={<SignUp />} />
        <Route path="*" element={<Error />} />
      </Routes>
    </div>
  );
}

export default App;