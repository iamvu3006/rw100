import React, { useState } from "react";

function SignUp(props) {
  // Khai báo các state để lưu dữ liệu cho các ô nhập liệu
  let [FullName, SetFullName] = useState("");
  let [Email, SetEmail] = useState("");
  let [Password, SetPassword] = useState("");
  let [RePassword, SetRePassword] = useState("");
  let [Birthday, SetBirthday] = useState("");

  // Hàm xử lý khi nhấn nút Register
  let handleRegister = () => {
    alert("FullName: " + FullName);
    alert("Email: " + Email);
    alert("Password: " + Password);
    alert("Re_Password: " + RePassword);
    alert("Birthday: " + Birthday);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4">
          <h3>Register System</h3>

          <div className="form-group">
            <label htmlFor="">Full Name: </label>
            <input
              type="text"
              className="form-control"
              placeholder="Enter Fullname"
              value={FullName}
              onChange={(event) => SetFullName(event.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="">Email: </label>
            <input
              type="email"
              className="form-control"
              placeholder="Enter Email"
              value={Email}
              onChange={(event) => SetEmail(event.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="">Password: </label>
            <input
              type="password"
              className="form-control"
              placeholder="Enter Password"
              value={Password}
              onChange={(event) => SetPassword(event.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="">Confirm Password: </label>
            <input
              type="password"
              className="form-control"
              placeholder="Enter RePassword"
              value={RePassword}
              onChange={(event) => SetRePassword(event.target.value)}
            />
          </div>
          <div className="form-group">
            <label htmlFor="">Birthday: </label>
            <input
              type="date"
              className="form-control"
              placeholder="Choose Birthday"
              value={Birthday}
              onChange={(event) => SetBirthday(event.target.value)}
            />
          </div>
          <div className="form-group">
            <input
              type="button"
              className="btn btn-success"
              onClick={handleRegister}
              value="Register"
            />
          </div>
        </div>
      </div>
    </div>
  );
}

export default SignUp;
