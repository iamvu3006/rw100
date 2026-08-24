import React from "react";
import {
  Button,
  Modal,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Container,
} from "reactstrap";


import InputForm from "./InputForm";


function ModalCreateNewAccount(props) {
  // Gọi lại các props truyền từ bên ngoài vào
  let { showForm, onHandleCloseModal, onHandleCreateNewAccount} = props;

  // Hàm xử lý khi nhấn nút Close
  let handleCloseModal = () => {
    onHandleCloseModal();
  };

  return (
    <Container>
      <br />
      <Modal isOpen={showForm}>
        <ModalHeader>
          <h3>Create New Account</h3>
        </ModalHeader>
        <ModalBody>
          <InputForm onHandleCreateNewAccount={onHandleCreateNewAccount}/>
        </ModalBody>
        <ModalFooter>
          <Button color="danger" onClick={handleCloseModal}>Close</Button>
        </ModalFooter>
      </Modal>
    </Container>
  );
}


export default ModalCreateNewAccount;