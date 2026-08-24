import React, { useState } from "react";
import "./App.css";
import ComponentsBottom from "./Component/ComponentsBottom";
import ComponentsTop from "./Component/ComponentsTop";
import LifeCycleDemo from "./Component/LifeCycleDemo";
function App() {
  // State lưu trữ dữ liệu truyền từ Top lên App
  const [data, setData] = useState("");

  // Hàm callback nhận dữ liệu từ ComponentsTop
  const getDataFromComponentTop = (data_param) => {
    console.log("APP nhận dữ liệu từ Top:", data_param);
    setData(data_param);
  };

  return (
    <div className="App">
      {/* <ComponentsTop
        heading_panel="------ Component Top -------"
        getData={getDataFromComponentTop} // Gửi kèm props là 1 hàm tới ComponentTop
      />
      <br />
      <br />
      <ComponentsBottom data={data} /> */}
      <LifeCycleDemo />
    </div>
  );
}

export default App;