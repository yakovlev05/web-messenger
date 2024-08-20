import {Button, Input, notification, Space} from "antd";
import {EyeInvisibleOutlined, EyeTwoTone} from "@ant-design/icons";
import {useState} from "react";
import {LoginRequest} from "../models/auth/LoginRequest.ts";
import {useNavigate} from "react-router-dom";

interface LoginFormComponentProps {
    onSubmit: (data: LoginRequest, setLoading: (bool: boolean) => void) => void;
}

const LoginFormComponent = (props: LoginFormComponentProps) => {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [statusForm, setStatusForm] = useState<{
        login: '' | "warning" | "error",
        password: '' | "warning" | "error"
    }>({login: '', password: ''});
    const [textForm, setTextForm] =
        useState<LoginRequest>({login: '', password: ''});


    const handleSubmit = () => {
        if (!checkForm()) return;

        props.onSubmit(textForm, setLoading);
    }

    const checkForm = () => {
        let flag: boolean = true;
        if (textForm.login.length < 5) {
            setStatusForm(prevState => ({...prevState, login: 'error'}));
            notification.warning({message: "Логин не может быть короче 5 символов"})
            flag = false;
        } else {
            setStatusForm(prevState => ({...prevState, login: ''}));
        }

        if (textForm.password.length < 8) {
            setStatusForm(prevState => ({...prevState, password: 'error'}));
            notification.warning({message: "Пароль не может быть короче 8 символов"})
            flag = false;
        } else {
            setStatusForm(prevState => ({...prevState, password: ''}));
        }

        return flag;
    }

    return (
        <div className="flex items-center justify-center flex-col min-h-screen mx-3">
            <h1 className="text-4xl mb-7">Вход</h1>
            <Space direction="vertical" className="w-full max-w-[400px]">
                <Input
                    placeholder="Имя пользователя"
                    status={statusForm.login}
                    onInput={e => setTextForm({...textForm, login: e.currentTarget.value})}
                />

                <Input.Password
                    placeholder="Пароль"
                    iconRender={(visible) => (visible ? <EyeTwoTone/> : <EyeInvisibleOutlined/>)}
                    status={statusForm.password}
                    onInput={e => setTextForm({...textForm, password: e.currentTarget.value})}
                />

                <Button type="primary"
                        loading={loading}
                        onClick={handleSubmit}>
                    Войти
                </Button>
            </Space>
            <div>
                <p className="mt-3">Нет аккаунта? 
                    <Button type="link" size="large" onClick={()=>navigate('/registration')}>Зарегистрироваться</Button>
                </p>
            </div>
        </div>
    )
}

export default LoginFormComponent;