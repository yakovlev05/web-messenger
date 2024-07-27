import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./pages/login";

function App() {
    return (
        <BrowserRouter>
            <main>
                <Routes>
                    <Route path="/" element={
                        <>
                            <h1>В разработке</h1>
                        </>
                    }/>
                    <Route path="/login" element={<LoginPage/>}/>
                </Routes>
            </main>
        </BrowserRouter>
    )
}

export default App
