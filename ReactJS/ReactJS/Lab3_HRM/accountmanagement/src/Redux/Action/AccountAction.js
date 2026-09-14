import { getListAccountAPI } from "../../api/AccountApi";
import * as TYPES from "../Contant/ActionType"; // Import acctionType đê sử dụng

// Viết các Action liên quan đến Call API
export const actionFetchListAccountAPI = () => {
  return (dispatch) => {
    return getListAccountAPI().then((response) => {
      //  console.log("reponseAPI:", response);
      dispatch(actionFetchListAccountRedux(response));
    });
  };
};

export const actionFetchListAccountRedux = (listAccount) => {
  return {
    type: TYPES.FETCH_ACCOUNT_LIST,
    payload: listAccount,
  };
};
