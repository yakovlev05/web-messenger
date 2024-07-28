import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./pages/login";
import RegistrationPage from "./pages/registration";
import ProfilePage from "./pages/profile";
import EditProfilePage from "./pages/editProfile";

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
                    <Route path='/me' element={<ProfilePage/>}/>
                    <Route path='/:username' element={<ProfilePage/>}/>
                    <Route path='/me/edit' element={<EditProfilePage/>}/>
                </Routes>
            </main>
        </BrowserRouter>
    )
}

export default App