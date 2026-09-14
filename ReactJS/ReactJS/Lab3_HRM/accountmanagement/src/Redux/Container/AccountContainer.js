import React, { useState } from "react";
import { useEffect } from "react";
import { Container } from "reactstrap";
import CreateButton from "../../Components/Account/CreateButton";
import ModalCreateNewAccount from "../../Components/Account/CreateNewAccount/ModalCreateNewAccount";
import ResultForm from "../../Components/Account/ResultForm";
// import Axios from "axios"; // Import thư viện Axios để sử dụng
import { addAccountNewAPI } from "../../api/AccountApi";
import { useDispatch } from "react-redux";
import { showFormAction } from "../Action/FormAction";
import { closeFormAction } from "../Action/FormAction";
import { actionFetchListAccountAPI } from "../Action/AccountAction";
import { actionFetchListDepartmentAPI } from "../Action/DepartmentAction";
import { actionFetchListPositionAPI } from "../Action/PositionAction";

function AccountContainer(props) {
  // Khai báo State để quản lý danh sách Account trên hệ thống
  // let [listAccount, setListAccount] = useState([]);
  // Khai báo State để quản lý danh sách Department
  // let [listDepartment, setListDepartment] = useState([]);
  // // Khai báo State để quản lý danh sách Position
  // let [listPosition, setListPosition] = useState([]);

  // Khai báo hook để dispach Action
  let dispatchRedux = useDispatch();

  // Hàm Callback xử lý khi nhấn nút CreateNewAccount
  let onHandleCreateButtuon = () => {
    dispatchRedux(showFormAction());
  };
  // Hàm Callback xử lý khi nhấn nút Close ở ModalCreateNewAccount
  let onHandleCloseModal = () => {
    dispatchRedux(closeFormAction());
  };
  // Hàm Callback xử lý khi nhấn nút Create ở InputForm
  let onHandleCreateNewAccount = (accountNew) => {
    let accountNew_API = {
      email: accountNew.email,
      username: accountNew.username,
      fullname: accountNew.fullname,
      departmentId: accountNew.department,
      positionId: accountNew.position,
    };
    // Gọi hàm call API
    addAccountNewAPI(accountNew_API).then((response) => {
      // Sau khi tạo dữ liệu thành công gọi hàm để load lại listAccount
      // fetchListAccount();
    });
    // Thực hiện đóng Form sau khi thêm mới
    dispatchRedux(closeFormAction());
  };

  // Hàm load dữ liệu API cho Account
  // let fetchListAccount = function () {
  //   getListAccountAPI().then((response) => {
  //     setListAccount(response);
  //   });
  // };

  // // Hàm load dữ liệu API cho Department
  // let fetchListDepartment = function () {
  //   getListDepartmentAPI().then((response) => {
  //     setListDepartment(response);
  //   });
  // };

  // // Hàm load dữ liệu API cho Position
  // let fetchListPosition = function () {
  //   getListPositionAPI().then((response) => {
  //     setListPosition(response);
  //   });
  // };

  // Khai báo useEffect khi component được mount
  useEffect(() => {
    // fetchListAccount();
    dispatchRedux(actionFetchListAccountAPI());
    dispatchRedux(actionFetchListDepartmentAPI());
    dispatchRedux(actionFetchListPositionAPI());
  }, []);

  return (
    <Container>
      {/* Nút thêm mới */}
      <CreateButton onHandleCreateButtuon={onHandleCreateButtuon} />
      {/* Form thêm mới Account*/}
      <ModalCreateNewAccount
        // showForm={showForm}
        onHandleCloseModal={onHandleCloseModal}
        onHandleCreateNewAccount={onHandleCreateNewAccount}
        // listDepartment={listDepartment}
        // listPosition={listPosition}
      />
      {/* Form kết quả */}
      <ResultForm />
    </Container>
  );
}

export default AccountContainer;
