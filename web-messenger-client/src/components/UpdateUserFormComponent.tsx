import {UserModel} from "../models/user/UserModel.ts";
import {Button, Flex, Form, Input, message, Popconfirm} from "antd";
import {useState} from "react";
import {useNavigate} from "react-router-dom";
import UpdateMyUserRequestApi from "../api/user/UpdateMyUserRequest.ts";
import DeleteMyUserRequestApi from "../api/user/DeleteMyUserRequest.ts";

interface UpdateUserFormComponentProps {
    data: UserModel;
    setData: (data: UserModel) => void;
}

const UpdateUserFormComponent = (props: UpdateUserFormComponentProps) => {
    const navigate = useNavigate();
    const [saveLoading, setSaveLoading] = useState(false);
    const [deleteLoading, setDeleteLoading] = useState(false);
    const [messageApi, contextHolder] = message.useMessage();

    const onSubmit = async () => {
        setSaveLoading(true);
        const response = UpdateMyUserRequestApi(props.data);

        response
            .then(async (r) => {
                if (r.ok) {
                    navigate('/me');
                } else {
                    const data = await r.json();
                    console.log(data.message)
                    if (data.message.includes('Username')) {
                        messageApi.error("Пользователь с таким именем уже существует!")
                    } else if (data.message.includes('Email')) {
                        messageApi.error("Пользователь с такой почтой уже существует!")
                    } else {
                        messageApi.error("Произошла ошибка при обновлении данных пользователя!")
                    }
                }
                setSaveLoading(false);
            })
            .catch(() => console.log("Ошибка при обновлении данных пользователя"))
    }

    const onDelete = async () => {
        setDeleteLoading(true);
        const response = DeleteMyUserRequestApi();

        response
            .then(async (r) => {
                if (r.ok) {
                    localStorage.removeItem("token");
                    navigate('/');
                } else messageApi.error("Произошла ошибка при удалении аккаунта")
            })
            .catch(() => console.log("Ошибка при удалении аккаунта"))
    }

    return (
        <>
            {contextHolder}
            <div className="flex items-center justify-center flex-col min-h-screen mx-3">
                <h1 className="text-4xl mb-7">Редактировать профиль</h1>
                <Form
                    style={{maxWidth: 400, width: '100%'}}
                    autoComplete="off"
                    onFinish={onSubmit}
                >
                    <Form.Item label="Имя"
                               name="name"
                               rules={[{required: true, message: "Имя не может быть пустым!"}]}
                               initialValue={props.data.name}>
                        <Input onChange={e => props.setData({...props.data, name: e.currentTarget.value})}/>
                    </Form.Item>

                    <Form.Item label="Фамилия"
                               name="surname"
                               rules={[{required: true, message: "Фамилия не может быть пустой!"}]}
                               initialValue={props.data.surname}>
                        <Input onChange={e => props.setData({...props.data, surname: e.currentTarget.value})}/>
                    </Form.Item>

                    <Form.Item
                        label="Имя пользователя"
                        name="username"
                        rules={[{required: true, message: 'Введите имя пользователя!'},
                            {min: 5, message: "Минимальная длина 5 символов"}]}
                        initialValue={props.data.username}>
                        <Input onChange={e => props.setData({...props.data, username: e.currentTarget.value})}/>
                    </Form.Item>

                    <Form.Item
                        label="Почта"
                        name="email"
                        rules={[{required: true, message: 'Введите почту!'},
                            {
                                pattern: RegExp("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"),
                                message: "Некорректная почта"
                            }]}
                        initialValue={props.data.email}>
                        <Input onChange={e => props.setData({...props.data, email: e.currentTarget.value})}/>
                    </Form.Item>

                    <Form.Item>
                        <Flex justify="space-between">
                            <Button type="primary"
                                    htmlType="submit"
                                    loading={saveLoading}>
                                Сохранить
                            </Button>

                            <Button type="dashed" onClick={() => navigate('/me')}>
                                Отмена
                            </Button>
                        </Flex>
                    </Form.Item>

                    <Flex justify="end">
                        <Popconfirm
                            title="Удаление аккаунта"
                            description="Вы точно хотите удалиьт аккаунт?"
                            onConfirm={onDelete}
                            okText="Да"
                            cancelText="Нет"
                            okButtonProps={{loading: deleteLoading}}>

                            <Button danger>Удалить аккаунт</Button>

                        </Popconfirm>
                    </Flex>
                </Form>
            </div>
        </>
    )
}

export default UpdateUserFormComponent;