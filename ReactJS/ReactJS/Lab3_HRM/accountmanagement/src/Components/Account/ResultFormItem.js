import React from "react";
import { Button } from "reactstrap";

function ResultFormItem(props) {
  // Lấy các props từ bên trên truyền xuống
  let { listAccount } = props;
  // Khai báo item hiển thị dữ liệu
  let items = "";
  // Kiểm tra nếu listAccount là mảng hợp lệ sẽ hiển thị dữ liệu
  if (listAccount && Array.isArray(listAccount)) {
    items = listAccount.map((account, index) => {
      let departmentDisplay = typeof account.department === "object" && account.department !== null 
        ? account.department.name 
        : account.department;

      let positionDisplay = typeof account.position === "object" && account.position !== null 
        ? account.position.name 
        : account.position;

      return (
        <tr key={account.id || index}>
          <td>{account.id}</td>
          <td>{account.email}</td>
          <td>{account.username}</td>
          <td>{account.fullname}</td>
          <td>{departmentDisplay}</td>
          <td>{positionDisplay}</td>
          <td>{account.createDate}</td>
          <td>
            <Button color="warning">Edit</Button>
          </td>
          <td>
            <Button color="danger">Delete</Button>
          </td>
        </tr>
      );
    });
  }
  return items;
}

export default ResultFormItem;