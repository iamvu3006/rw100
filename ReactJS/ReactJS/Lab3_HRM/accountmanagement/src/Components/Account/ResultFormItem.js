import React from "react";
import { Button } from "reactstrap";

function ResultFormItem(props) {
  // Lấy các props từ bên trên truyền xuống
  let { listAccount } = props;
  // Khai báo item hiển thị dữ liệu
  let items = "";
  // Kiểm tra nếu listAccount !="" sẽ hiển thị dữ liệu
  if (listAccount) {
    items = listAccount.map((account, index) => {
      return (
        <tr key={index}>
          <td>{account.id}</td>
          <td>{account.email}</td>
          <td>{account.username}</td>
          <td>{account.fullname}</td>
          <td>{account.department}</td>
          <td>{account.position}</td>
          <td>{account.createDate}</td>
          <td>
            <Button color="warning">Edit</Button>
          </td>
          <td>
            <Button color="warning">Delete</Button>
          </td>
        </tr>
      );
    });
  }
  return items;
}

export default ResultFormItem;