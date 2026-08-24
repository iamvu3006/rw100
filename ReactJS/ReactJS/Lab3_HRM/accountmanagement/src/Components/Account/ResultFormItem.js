import React from "react";
import { Button } from "reactstrap";

function ResultFormItem(props) {
  return (
    <tr>
      <td>1</td>
      <td>daonq@viettel.com.vn</td>
      <td>daonq</td>
      <td>Nguyen Dao</td>
      <td>Sale</td>
      <td>Dev</td>
      <td>2022-02-22</td>
      <td>
        <Button color="warning">Edit</Button>
      </td>
      <td>
        <Button color="warning">Delete</Button>
      </td>
    </tr>
  );
}

export default ResultFormItem;