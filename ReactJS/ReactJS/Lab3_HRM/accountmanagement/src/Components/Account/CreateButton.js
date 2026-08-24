import React from "react";
import { Container, Button } from "reactstrap";


function CreateButton(props) {
  // Gọi lại các props từ bên trên truyền xuống
  let { onHandleCreateButtuon } = props;
  // Hàm xử lý khi click vào nút CreateNewAccount
  let handleCreateNewAccount = () => {
    onHandleCreateButtuon();
  };
  //
  return (
    <Container>
      <br />
      <Button color="primary" onClick={handleCreateNewAccount}>
        Create New Account
      </Button>
    </Container>
  );
}

export default CreateButton;
