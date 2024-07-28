import LoginFormComponent from "../../components/LoginFormComponent.tsx";
import {LoginRequest} from "../../models/auth/LoginRequest.ts";
import LoginRequestApi from "../../api/auth/LoginRequest.ts";
import {useNavigate} from "react-router-dom";
import {TokenResponse} from "../../models/auth/TokenResponse.ts";
import {notification} from "antd";


const LoginPage = () => {
    const navigate = useNavigate();

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

    return (
        <LoginFormComponent
            onSubmit={onSubmit}
        />
    )
}

export default LoginPage;