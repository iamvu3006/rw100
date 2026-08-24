// https://reactstrap.github.io/?path=/story/home-installation--page


import React from "react";
import { Container, Button, Form, FormGroup, Label, Input } from "reactstrap";


function InputForm(props) {
  return (
    <Container>
      <Form>
        {/* Email */}
        <FormGroup>
          <Label for="Email">Email: </Label>
          <Input
            id="Email"
            name="Email"
            placeholder="Input Email"
            type="email"
          />
        </FormGroup>


        {/* Username */}
        <FormGroup>
          <Label for="Username">Username: </Label>
          <Input
            id="Username"
            name="Username"
            placeholder="Input Username"
            type="text"
          />
        </FormGroup>


        {/* Fullname */}
        <FormGroup>
          <Label for="Fullname">Fullname: </Label>
          <Input
            id="Fullname"
            name="Fullname"
            placeholder="Input Fullname"
            type="text"
          />
        </FormGroup>


        {/* Department */}
        <FormGroup>
          <Label for="Department">Select a Department: </Label>
          <Input id="Department" name="Department" type="select">
            <option value={"Bán hàng"}>Bán hàng</option>
            <option value={"Bảo vệ"}>Bảo vệ</option>
            <option value={"Giám đốc"}>Giám đốc</option>
            <option value={"Kỹ thuật"}>Kỹ thuật</option>
            <option value={"Marketing"}>Marketing</option>
          </Input>
        </FormGroup>


        {/* Postion */}
        <FormGroup>
          <Label for="Postion">Select a Postion: </Label>
          <Input id="Postion" name="Postion" type="select">
           <option value={"Dev"}>Dev</option>
            <option value={"Test"}>Test</option>
            <option value={"Scrum_Master"}>Scrum_Master</option>
            <option value={"PM"}>PM</option>
          </Input>
        </FormGroup>
      </Form>
      {/* Nút xử lý */}
      <Button color="primary">Create</Button>
      <Button color="danger">Reset</Button>
    </Container>
  );
}


export default InputForm;