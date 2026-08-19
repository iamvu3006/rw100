import logo from "./logo.svg";
import "./App.css";

function App() {
    let handleClick = () => {
        alert("Hello VTI!");
        console.log("Hello VTI!");
    };
    return (
        <div className="App">
            <button
                onClick={handleClick}
            >
                Click me
            </button>
        </div>
    );

}

export default App;
