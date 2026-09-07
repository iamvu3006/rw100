import "./App.css";
import Menu from "./Components/Menu/Menu";
import { routes } from "./Router/Route";

function App() {
  return (
    <div className="container">
      {/* Menu */}
      <Menu />
      {/* Page-Content */}
      {/* Khai báo bảng định tuyến */}
      {routes}
      {/* */}
    </div>
  );
}

export default App;