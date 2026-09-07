import React from "react";
import { Link } from "react-router-dom";

function Menu() {
  return (
    <div className="row">
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
  );
}

export default Menu;
