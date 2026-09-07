import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function AccountManagement(props) {
  //
  let [listAccounts, setListAccounts] = useState([]);
  // Khai báo Hook useNavigate
  let navigate = useNavigate();
  // Check xem user đã login chưa
  useEffect(() => {
    let user_login = JSON.parse(localStorage.getItem("user_login"));

    if (!user_login) {
      // TH này khi User đã login sẽ chuyển tới trang home
      return navigate("/Login");
    }

    let listAccounts_API_FAKE = [
      {
        ID: "1",
        Email: "Email1@gmail.com",
      },
      {
        ID: "2",
        Email: "Email2@gmail.com",
      },
      {
        ID: "3",
        Email: "Email3@gmail.com",
      },
      {
        ID: "4",
        Email: "Email4@gmail.com",
      },
    ];

    setListAccounts(listAccounts_API_FAKE);
  }, []);
  //
  const rowItem = listAccounts.map((account, index) => {
    let url = "/AccountDetail/" + account.ID;
    return (
      <>
        <tr>
          <td> {account.ID}</td>
          <td>
            <Link to={url}>{account.Email}</Link>
          </td>
        </tr>
      </>
    );
  });

  //
  return (
    <div className="row">
      <h3>Thông tin Account</h3>
      <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
        <table className="table table-hover">
          <thead>
            <tr>
              <td>ID</td>
              <td>Email</td>
            </tr>
          </thead>
          <tbody>
            {/* In các row */}
            {rowItem}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default AccountManagement;