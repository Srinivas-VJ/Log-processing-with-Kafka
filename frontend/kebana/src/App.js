import logo from "./logo.svg";
import "./App.css";
import { Header } from "./Header";
import { Logs } from "./Logs";

function App() {
  return (
    <div className="App">
      <Header logo={logo} title={"Kebana"} />
      <Logs />
    </div>
  );
}

export default App;
