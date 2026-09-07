import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login(props) {
  // Khai báo các state để lưu dữ liệu cho các ô nhập liệu
  let [Email, SetEmail] = useState("");
  let [Password, SetPassword] = useState("");

  let navigate = useNavigate();
  // Hàm xử lý khi nhấn nút Login
  let handleLogin = () => {
    // alert("Email: " + Email);
    // alert("Password: " + Password);
    alert("Login Success!");
    navigate("/AccountManagement");
  };

  return (
    <div className="container" style={{ width: "70%" }}>
      <div className="row">
        <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4">
          <br />
          <br />
          <br />
          <h3>LOGIN</h3>
          <br />
          <div className="form-group">
            <label htmlFor="Email">Email:</label>
            <input
              type="email"
              className="form-control"
              placeholder="Enter email"
              value={Email}
              onChange={(event) => SetEmail(event.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="Password">Password:</label>
            <input
              type="Password"
              className="form-control"
              placeholder="Enter Password"
              value={Password}
              onChange={(event) => SetPassword(event.target.value)}
            />
          </div>
          <button type="button" className="btn btn-info" onClick={handleLogin}>
            Login
          </button>
        </div>
      </div>
    </div>
  );
}

export default Login;