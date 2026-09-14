import { combineReducers } from "redux";
import FormReducer from "./FormReducer";

const RootReducers = combineReducers({
  showForm: FormReducer,
});

export default RootReducers;
