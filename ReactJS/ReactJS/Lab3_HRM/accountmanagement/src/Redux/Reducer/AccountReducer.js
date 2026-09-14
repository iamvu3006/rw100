import * as TYPES from "../Contant/ActionType"; // Import acctionType đê sử dụng

var initialState = [];

const AccountReducer = (state = initialState, action) => {
  switch (action.type) {
    case TYPES.FETCH_ACCOUNT_LIST:
      //   console.log("payload: ", action.payload);
      state = action.payload;
      return [...state];

    default:
      return [...state];
  }
};

export default AccountReducer;