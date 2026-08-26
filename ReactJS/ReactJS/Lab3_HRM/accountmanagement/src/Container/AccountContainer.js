import React, { useState } from "react";
import { useEffect } from "react";
import { Container } from "reactstrap";
import CreateButton from "../Components/Account/CreateButton";
import ModalCreateNewAccount from "../Components/Account/CreateNewAccount/ModalCreateNewAccount";
import ResultForm from "../Components/Account/ResultForm";
import Axios from "axios";


function AccountContainer(props) {
  // Khai báo State để quản lý trạng thái đóng mở của ModalCreateNewAccount(InputForm)
  let [showForm, setShowForm] = useState(false);
  // Khai báo State để quản lý danh sách Account trên hệ thống
  let [listAccount, setListAccount] = useState([]);

  // Hàm Callback xử lý khi nhấn nút CreateNewAccount
  let onHandleCreateButtuon = () => {
    // console.log("click!!");
    setShowForm(true);
  };
  // Hàm Callback xử lý khi nhấn nút Close ở ModalCreateNewAccount
  let onHandleCloseModal = () => {
    // console.log("click!!");
    setShowForm(false);
  };
  // Hàm Callback xử lý khi nhấn nút Create ở InputForm
  let onHandleCreateNewAccount = (accountNew) => {
    // console.log("accountNew: ", accountNew);
    listAccount.push(accountNew);
    // Set lại state listAccount
    setListAccount(listAccount);
    // Lưu dữ liệu vào LocalStorage
    localStorage.setItem("listAccount", JSON.stringify(listAccount));
  // Thực hiện đóng Form sau khi thêm mới
    setShowForm(false);
  };

    //
  let fetchListAccount = function () {
    const baseURL = `http://localhost:8080`; // Địa chỉ Server

    Axios.get(`${baseURL}/api/v1/accounts`)
      .then((response) => {
        // console.log(response.data);
        let listAccounts_API = response.data; // ListAcconunt nhân được khi Call API
        setListAccount(listAccounts_API); // Set lại State
      })
      .catch((error) => console.log(error));
  };

  // Khai báo useEffect, useEffect này khi component được mount và mỗi khi State: listAccount thay đổi
  useEffect(() => {
    fetchListAccount();
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
      />
      {/* Form kết quả */}
      <ResultForm listAccount = {listAccount}></ResultForm>
    </Container>
  );
}


export default AccountContainer;