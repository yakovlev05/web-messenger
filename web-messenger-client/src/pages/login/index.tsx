import LoginFormComponent from "../../components/LoginFormComponent.tsx";
import {LoginRequest} from "../../models/auth/LoginRequest.ts";
import LoginRequestApi from "../../api/auth/LoginRequest.ts";
import {useNavigate} from "react-router-dom";
import {TokenResponse} from "../../models/auth/TokenResponse.ts";
import {notification} from "antd";
import {useEffect, useState} from "react";
import GetMyUserRequest from "../../api/user/GetMyUserRequest.ts";
import LoaderComponent from "../../components/LoaderComponent.tsx";


const LoginPage = () => {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(true);

    const onSubmit = (data: LoginRequest, setLoading: (bool: boolean) => void) => {
        setLoading(true);
        const response = LoginRequestApi(data);

        response
            .then(async r => {
                if (r.ok) {
                    const data: TokenResponse = await r.json();
                    localStorage.setItem("token", data.token);
                    navigate("/me")
                } else {
                    setLoading(false);
                    notification.error({message: "Неверный логин или пароль"})
                }
            })
            .catch(() => console.log("Ошибка отправки запроса"))
    }

    useEffect(() => {
        const fetchData = async () => {
            if (!localStorage.getItem("token")) return;
            const response = await GetMyUserRequest();
            if (response.ok) {
                navigate("/me");
            }
        }

        fetchData()
            .then(() => setTimeout(() => setLoading(false), 500));
    }, [navigate]);

    if (loading) {
        return (<LoaderComponent/>)
    }

    return (
        <LoginFormComponent
            onSubmit={onSubmit}
        />
    )
}

export default LoginPage;