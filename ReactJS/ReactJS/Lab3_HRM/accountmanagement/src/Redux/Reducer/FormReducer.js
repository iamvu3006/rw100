import * as TYPES from "../Contant/ActionType"; // Import acctionType đê sử dụng

// Khai báo các state showForm để quản lý
var initialState = false;

// Khai báo reducer, truyền vào các state mặc định
var FormReducer = (state = initialState, action) => {
  switch (action.type) {
    case TYPES.SHOW_FORM: // Xử lý Action showForm
      return true;

    case TYPES.CLOSE_FORM: // Xử lý Action showForm
      return false;
    default:
      return state; // Chú ý cần return về state mặc định nếu không sẽ gặp lỗi.
  }
};

export default FormReducer;