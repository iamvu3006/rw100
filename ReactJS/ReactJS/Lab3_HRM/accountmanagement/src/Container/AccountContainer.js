import React, { useState } from "react";
import { useEffect } from "react";
import { Container } from "reactstrap";
import CreateButton from "../Components/Account/CreateButton";
import ModalCreateNewAccount from "../Components/Account/CreateNewAccount/ModalCreateNewAccount";
import ResultForm from "../Components/Account/ResultForm";
// import Axios from "axios"; // Import thư viện Axios để sử dụng
import { getListAccountAPI } from "../api/AccountApi";
import { getListDepartmentAPI } from "../api/DepartmentApi";
import { getListPositionAPI } from "../api/PositionApi";

function AccountContainer(props) {
  // Khai báo State để quản lý trạng thái đóng mở của ModalCreateNewAccount(InputForm)
  let [showForm, setShowForm] = useState(false);
  // Khai báo State để quản lý danh sách Account trên hệ thống
  let [listAccount, setListAccount] = useState([]);
  // Khai báo State để quản lý danh sách Department
  let [listDepartment, setListDepartment] = useState([]);
  // Khai báo State để quản lý danh sách Position
  let [listPosition, setListPosition] = useState([]);

  // Hàm Callback xử lý khi nhấn nút CreateNewAccount
  let onHandleCreateButtuon = () => {
    setShowForm(true);
  };
  // Hàm Callback xử lý khi nhấn nút Close ở ModalCreateNewAccount
  let onHandleCloseModal = () => {
    setShowForm(false);
  };
  // Hàm Callback xử lý khi nhấn nút Create ở InputForm
  let onHandleCreateNewAccount = (accountNew) => {
    let updatedList = [...listAccount, accountNew];
    setListAccount(updatedList);
    localStorage.setItem("listAccount", JSON.stringify(updatedList));
    // Thực hiện đóng Form sau khi thêm mới
    setShowForm(false);
  };

  // Hàm load dữ liệu API cho Account
  let fetchListAccount = function () {
    const baseURL = `http://localhost:8080`; // Địa chỉ Server

    Axios.get(`${baseURL}/api/v1/accounts`)
      .then((response) => {
        let listAccounts_API = response.data; // ListAccount nhận được khi Call API
        setListAccount(listAccounts_API); // Set lại State
      })
      .catch((error) => console.log("Lỗi fetchListAccount:", error));
  };

  // Hàm load dữ liệu API cho Department
  let fetchListDepartment = function () {
    const baseURL = `http://localhost:8080`; // Link địa chỉ Server
    Axios.get(`${baseURL}/api/v1/departments`)
      .then((response) => {
        let listDepartment_API = response.data; // ListDepartment nhận được khi Call API
        setListDepartment(listDepartment_API); // Set lại State
      })
      .catch((error) => console.log("Lỗi fetchListDepartment:", error));
  };

  // Hàm load dữ liệu API cho Position
  let fetchListPosition = function () {
    const baseURL = `http://localhost:8080`; // Link địa chỉ Server
    Axios.get(`${baseURL}/api/v1/possitions`)
      .then((response) => {
        let listPosition_API = response.data; // ListPosition nhận được khi Call API
        setListPosition(listPosition_API); // Set lại State
      })
      .catch((error) => console.log("Lỗi fetchListPosition:", error));
  };

  // Khai báo useEffect khi component được mount
  useEffect(() => {
    fetchListAccount();
    fetchListDepartment();
    fetchListPosition();
  }, []);

  return (
    <Container>
      {/* Nút thêm mới */}
      <CreateButton onHandleCreateButtuon={onHandleCreateButtuon} />
      {/* Form thêm mới Account*/}
      <ModalCreateNewAccount
        showForm={showForm}
        onHandleCloseModal={onHandleCloseModal}
        onHandleCreateNewAccount={onHandleCreateNewAccount}
        listDepartment={listDepartment}
        listPosition={listPosition}
      />
      {/* Form kết quả */}
      <ResultForm listAccount={listAccount}></ResultForm>
    </Container>
  );
}

export default AccountContainer;
