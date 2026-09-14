import { combineReducers } from "redux";
import AccountReducer from "./AccountReducer";
import FormReducer from "./FormReducer";

const RootReducers = combineReducers({
  showForm: FormReducer,
  listAccount: AccountReducer,
});

export default RootReducers;
