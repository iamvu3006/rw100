import { combineReducers } from "redux";
import AccountReducer from "./AccountReducer";
import FormReducer from "./FormReducer";
import DepartmentReducer from "./DepartmentReducer";
import PositionReducer from "./PositionReducer";

const RootReducers = combineReducers({
  showForm: FormReducer,
  listAccount: AccountReducer,
  listDepartment: DepartmentReducer,
  listPosition: PositionReducer,
});

export default RootReducers;
