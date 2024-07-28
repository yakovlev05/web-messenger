import {Button, Input, Space} from "antd";
import {EyeInvisibleOutlined, EyeTwoTone} from "@ant-design/icons";
import React, {useState} from "react";

interface LoginFormComponentProps {
    onInputLogin: (event: React.FormEvent<HTMLInputElement>) => void;
    onInputPassword: (event: React.FormEvent<HTMLInputElement>) => void;
    onSubmit: () => void;
}

const LoginFormComponent = (props: LoginFormComponentProps) => {
    const [loading, setLoading] = useState(false);

    const handleSubmit = () => {
        setLoading(true);
        props.onSubmit();
    }


    return (
        <div className="flex items-center justify-center flex-col min-h-screen mx-3">
            <h1 className="text-4xl mb-7">Вход</h1>
            <Space direction="vertical" className="w-full max-w-[400px]">
                <Input
                    placeholder="Имя пользователя"
                    className=""
                    onInput={props.onInputLogin}
                />

                <Input.Password
                    placeholder="Пароль"
                    iconRender={(visible) => (visible ? <EyeTwoTone/> : <EyeInvisibleOutlined/>)}
                    onInput={props.onInputPassword}
                />

                <Button type="primary"
                        loading={loading}
                        onSubmit={handleSubmit}>
                    Войти
                </Button>
            </Space>
        </div>
    )
}

export default LoginFormComponent;