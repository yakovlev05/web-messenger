import RegistrationFormComponent from "../../components/RegistrationFormComponent.tsx";
import {useNavigate} from "react-router-dom";
import {RegistrationRequest} from "../../models/auth/RegistrationRequest.ts";
import RegistrationRequestApi from "../../api/auth/RegistrationRequest.ts";
import {TokenResponse} from "../../models/auth/TokenResponse.ts";
import {notification} from "antd";

const RegistrationPage = () => {
    const navigate = useNavigate();

    const onSubmit = (data: RegistrationRequest, setLoading: (bool: boolean) => void) => {
        setLoading(true);
        const response = RegistrationRequestApi(data);

        response
            .then(async r => {
                if (r.ok) {
                    const data: TokenResponse = await r.json();
                    localStorage.setItem("token", data.token);
                    navigate("/me")
                } else {
                    setLoading(false);
                    const data = await r.json();
                    data.message ?
                        notification.error({message: "Логин или почта заняты"})
                        : notification.error({message: "Ошибка регистрации"})
                }
            })
            .catch(() => console.log("Ошибка отправки запроса регистрации"))
    }

    return (
        <RegistrationFormComponent onSubmit={onSubmit}/>
    )
}

export default RegistrationPage;