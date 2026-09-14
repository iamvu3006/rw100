import * as TYPES from "../Contant/ActionType";

// action dùng mở Modal InputForm
export const showFormAction = () => {
  return {
    type: TYPES.SHOW_FORM, //
  };
};

// action dùng đóng Modal InputForm
export const closeFormAction = () => {
  return {
    type: TYPES.CLOSE_FORM,
  };
};
