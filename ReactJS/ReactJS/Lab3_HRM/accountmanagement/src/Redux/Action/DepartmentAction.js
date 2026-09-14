import { getListDepartmentAPI } from "../../api/DepartmentApi";
import * as TYPES from "../Contant/ActionType"; // Import acctionType đê sử dụng

// Viết các Action liên quan đến Call API
export const actionFetchListDepartmentAPI = () => {
  return (dispatch) => {
    return getListDepartmentAPI().then((response) => {
      //  console.log("reponseAPI:", response);
      dispatch(actionFetchListDepartmentRedux(response));
    });
  };
};

export const actionFetchListDepartmentRedux = (listDepartment) => {
  return {
    type: TYPES.FETCH_DEPARTMENT_LIST,
    payload: listDepartment,
  };
};
