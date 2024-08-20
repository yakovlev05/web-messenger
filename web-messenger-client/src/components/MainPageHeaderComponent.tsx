import {Button, Spin} from "antd";
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import GetMyUserRequest from "../api/user/GetMyUserRequest.ts";

const MainPageHeaderComponent = () => {
    const navigation = useNavigate();
    const [loading, setLoading] = useState(true);
    const [logged, setLogged] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            if (!localStorage.getItem("token")) return;
            const response = await GetMyUserRequest();
            if (response.ok) {
                setLogged(true);
            }
        }

        fetchData()
            .then(() => setTimeout(() => setLoading(false), 500));
    }, []);

    return (
        <div className="flex flex-wrap gap-x-10 gap-y-4 items-center justify-center mt-4">
            <a className="flex gap-4 items-center" href="/">
                <img src="/src/assets/message.svg" alt="logo" width="32" height="32"/>
                <h4 className="font-semibold text-[20px] whitespace-nowrap">Web-messenger</h4>
            </a>
            <div className="flex gap-2 items-center">
                <Button type="dashed" onClick={() => navigation('/login')} hidden={logged || loading}>Вход</Button>
                <Button type="primary" onClick={() => navigation('/registration')}
                        hidden={logged || loading}>Регистрация</Button>
                <Button type="default" onClick={() => navigation('/me')} hidden={!logged || loading}>Профиль</Button>
                {loading && <Spin spinning={loading}/>}
            </div>
        </div>
    )
}

export default MainPageHeaderComponent;