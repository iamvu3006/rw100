import { Route, Routes } from "react-router-dom";
import Home from "../Components/Home";
import About from "../Components/About";
import AccountManagement from "../Components/AccountManagement";
import DepartmentManagement from "../Components/DepartmentManagement";
import Login from "../Components/Login";
import SignUp from "../Components/SignUp";
import Error from "./../Components/Error";
import AccountDetail from "../Components/AccountDetail";

let routes = (
  <Routes>
    <Route path="/" element={<Home />} />
    <Route path="/home" element={<Home />} />
    <Route path="/about" element={<About />} />
    <Route path="/AccountManagement" element={<AccountManagement />} />
    <Route path="/DepartmentManagement" element={<DepartmentManagement />} />
    <Route path="/Login" element={<Login />} />
    <Route path="/Sign_Up" element={<SignUp />} />
    <Route path="/AccountDetail/:ID" element={<AccountDetail />} exact />
    <Route path="*" element={<Error />} />
  </Routes>
);

export { routes };
