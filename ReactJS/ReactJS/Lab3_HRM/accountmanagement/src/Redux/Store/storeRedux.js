import { createStore } from "redux";
import RootReducers from "../Reducer/RootReducer";

const storeRedux = createStore(
  RootReducers,
  window.__REDUX_DEVTOOLS_EXTENSION__ &&
  window.__REDUX_DEVTOOLS_EXTENSION__({ trace: true })
);

export default storeRedux;