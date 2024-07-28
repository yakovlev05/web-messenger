import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./pages/login";
import RegistrationPage from "./pages/registration";

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
                    <Route path="/registration" element={<RegistrationPage/>}/>
                </Routes>
            </main>
        </BrowserRouter>
    )
}

export default App
