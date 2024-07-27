import './App.css'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./home.tsx";
import About from "./about.tsx";

function App() {
    return (
        <BrowserRouter>
            <main>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/about" element={<About/>}/>
                </Routes>
            </main>
        </BrowserRouter>
    )
}

export default App
