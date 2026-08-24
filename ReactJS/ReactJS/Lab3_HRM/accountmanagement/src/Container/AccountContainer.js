import React from "react";
import { Container } from "reactstrap";
import CreateButton from "../Components/Account/CreateButton";
import ModalCreateNewAccount from "../Components/Account/CreateNewAccount/ModalCreateNewAccount";
import ResultForm from "../Components/Account/ResultForm";


function AccountContainer(props) {
  return (
    <Container>
      {/* Nút thêm mới */}
      <CreateButton />
      {/* Form thêm mới Account*/}
      <ModalCreateNewAccount />
      {/* Form kết quả */}
      <ResultForm></ResultForm>
    </Container>
  );
}

export default AccountContainer;