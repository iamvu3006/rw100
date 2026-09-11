import { createStore } from "redux";
import RootReducers from "../Reducer/RootReducer";

const storeRedux = createStore(RootReducers);

export default storeRedux;
