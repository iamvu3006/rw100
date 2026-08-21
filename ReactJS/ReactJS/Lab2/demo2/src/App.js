import React, { useState } from "react";
import "./App.css";
import ComponentsBottom from "./Component/ComponentsBottom";
import ComponentsTop from "./Component/ComponentsTop";

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
      <ComponentsTop
        heading_panel="------ Component Top -------"
        getData={getDataFromComponentTop} // Gửi kèm props là 1 hàm tới ComponentTop
        dataToTopChild={"This is data From App to Top_Child"}
      />
      <br />
      <br />
      {/* Gửi prop data xuống ComponentBottom */}
      <ComponentsBottom message="Welcome to VTI Academy!!" data={data} />
    </div>
  );
}

export default App;