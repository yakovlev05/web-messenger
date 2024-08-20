import RegistrationFormComponent from "../../components/RegistrationFormComponent.tsx";
import {useNavigate} from "react-router-dom";
import {RegistrationRequest} from "../../models/auth/RegistrationRequest.ts";
import RegistrationRequestApi from "../../api/auth/RegistrationRequest.ts";
import {TokenResponse} from "../../models/auth/TokenResponse.ts";
import {notification} from "antd";
import {useEffect, useState} from "react";
import LoaderComponent from "../../components/LoaderComponent.tsx";
import GetMyUserRequest from "../../api/user/GetMyUserRequest.ts";

const RegistrationPage = () => {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(true);

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
        <RegistrationFormComponent onSubmit={onSubmit}/>
    )
}

export default RegistrationPage;