import {RegistrationRequest} from "../models/auth/RegistrationRequest.ts";
import {useState} from "react";
import {Button, Form, Input} from "antd";
import {useNavigate} from "react-router-dom";

interface RegistrationFormComponentProps {
    onSubmit: (data: RegistrationRequest, setLoading: (bool: boolean) => void) => void;
}

const RegistrationFormComponent = (props: RegistrationFormComponentProps) => {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [textForm, setTextForm] =
        useState<RegistrationRequest>({
            name: '',
            surname: '',
            username: '',
            password: '',
            email: ''
        });

    return (
        <div className="flex items-center justify-center flex-col min-h-screen mx-3">
            <h1 className="text-4xl mb-7">Регистрация</h1>
            <Form
                // labelCol={{span:9}}
                // wrapperCol={{span: 5}}
                style={{maxWidth: 400, width: '100%'}}
                // style={{width: '100%'}}
                // onFinish={onFinish}
                // onFinishFailed={onFinishFailed}
                autoComplete="off"
                onFinish={() => props.onSubmit(textForm, setLoading)}
            >
                <Form.Item label="Имя"
                           name="name"
                           rules={[{required: true, message: "Имя не может быть пустым!"}]}>
                    <Input onChange={e => setTextForm({...textForm, name: e.currentTarget.value})}/>
                </Form.Item>

                <Form.Item label="Фамилия"
                           name="surname"
                           rules={[{required: true, message: "Фамилия не может быть пустой!"}]}>
                    <Input onChange={e => setTextForm({...textForm, surname: e.currentTarget.value})}/>
                </Form.Item>

                <Form.Item
                    label="Имя пользователя"
                    name="username"
                    rules={[{required: true, message: 'Введите имя пользователя!'},
                        {min: 5, message: "Минимальная длина 5 символов"}]}
                >
                    <Input onChange={e => setTextForm({...textForm, username: e.currentTarget.value})}/>
                </Form.Item>

                <Form.Item
                    label="Почта"
                    name="email"
                    rules={[{required: true, message: 'Введите почту!'},
                        {pattern: RegExp("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"), message: "Некорректная почта"}]}
                >
                    <Input onChange={e => setTextForm({...textForm, email: e.currentTarget.value})}/>
                </Form.Item>

                <Form.Item
                    label="Пароль"
                    name="password"
                    rules={[{required: true, message: 'Введите пароль!'},
                        {min: 8, message: "Минимальная длина пароля 8 символов"}]}
                >
                    <Input.Password onChange={e => setTextForm({...textForm, password: e.currentTarget.value})}/>
                </Form.Item>

                <Form.Item>
                    <Button type="primary"
                            htmlType="submit"
                            loading={loading}>
                        Регистрация
                    </Button>
                </Form.Item>
            </Form>
            <div>
                <p className="mt-3">Уже зарегистрированы?
                    <Button type="link" size="large"
                            onClick={() => navigate('/login')}>Войти</Button>
                </p>
            </div>
        </div>
    )
}

export default RegistrationFormComponent;