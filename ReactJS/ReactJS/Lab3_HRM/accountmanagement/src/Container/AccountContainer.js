import React from "react";
import { Container } from "reactstrap";
import CreateButton from "../Components/Account/CreateButton";
import ResultForm from "../Components/Account/ResultForm";


function AccountContainer(props) {
  return (
    <Container>
      {/* Nút thêm mới */}
      <CreateButton />
      {/* Form kết quả */}
      <ResultForm></ResultForm>
    </Container>
  );
}

export default AccountContainer;