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
    <div class="container">
      {/* Menu */}
      <nav
        className="navbar navbar-inverse"
        style={{ backgroundColor: "black", color: "black" }}
      >
        <div className="container-fluid">
          <div className="navbar-header">
            {/* <a className="navbar-brand" href="#">VTI ACADEMY</a> Chuyển thẻ này xuống phía dưới để không bị ảnh hưởng Css Boostrap*/}
          </div>
          <ul class="nav navbar-nav">
            {/* <li><a href="#">Tramg chủ</a></li>
              <li><a href="#">Khóa học</a></li>
              <li><a href="#">Về chúng tôi</a></li>
              <li><a href="#">Học viên</a></li>
              <li><a href="#">Bài thi</a></li>
              <li><a href="#">Đào tạo doanh nghiệp</a></li> 
              Commnet đoạn này lại do không sử dụng thẻ a, thay thế bằng thẻ Link trong router.
              */}
            <li>
              <Link to="/">VTI ACADEMY</Link>
              {/* Chuyển từ phía trên xuống, tạo thành 1 thẻ li */}
            </li>
            <li>
              <Link to="/home">Home</Link>
            </li>
            <li>
              <Link to="/about">About</Link>
            </li>
            <li>
              <Link to="/AccountManagement">AccountManagement</Link>
            </li>
            <li>
              <Link to="/DepartmentManagement">DepartmentManagement</Link>
            </li>
          </ul>
          <ul className="nav navbar-nav navbar-right">
            <li>
              {/* <a href="#"> */}
              <Link to="/Sign_Up">
                <span className="glyphicon glyphicon-user"></span> Sign Up
              </Link>
            </li>
            <li>
              {/* <a href="#"> */}
              <Link to="/Login">
                <span className="glyphicon glyphicon-log-in"></span> Login
              </Link>
              {/* </a> */}
            </li>
          </ul>
        </div>
      </nav>

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
