import {Button, Input, Space} from 'antd';
import {EyeInvisibleOutlined, EyeTwoTone} from "@ant-design/icons";

const LoginPage = () => {
    return (
        <div className="flex items-center justify-center flex-col min-h-screen mx-3">
            <h1 className="text-4xl mb-7">Вход</h1>
            <Space direction="vertical" className="w-full max-w-[400px]">
                <Input
                    placeholder="Имя пользователя"
                    className=""
                />

                <Input.Password
                    placeholder="Пароль"
                    iconRender={(visible) => (visible ? <EyeTwoTone/> : <EyeInvisibleOutlined/>)}
                />

                <Button type="primary"
                        loading={false}>
                    Войти
                </Button>
            </Space>
        </div>
    )
}

export default LoginPage;