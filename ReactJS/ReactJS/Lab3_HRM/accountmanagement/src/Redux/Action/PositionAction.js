import { getListPositionAPI } from "../../api/PositionApi";
import * as TYPES from "../Contant/ActionType"; // Import acctionType đê sử dụng

// Viết các Action liên quan đến Call API
export const actionFetchListPositionAPI = () => {
  return (dispatch) => {
    return getListPositionAPI().then((response) => {
      //  console.log("reponseAPI:", response);
      dispatch(actionFetchListPositionRedux(response));
    });
  };
};

export const actionFetchListPositionRedux = (listPosition) => {
  return {
    type: TYPES.FETCH_POSITION_LIST,
    payload: listPosition,
  };
};
