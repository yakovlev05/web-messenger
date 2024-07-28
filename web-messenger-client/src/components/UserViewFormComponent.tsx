import {UserModel} from "../models/user/UserModel.ts";
import {Button, Flex, Typography} from "antd";
import {useNavigate} from "react-router-dom";

interface UserViewFormProps {
    data: UserModel;
    isMyAccount: boolean;
}

const UserViewFormComponent = (props: UserViewFormProps) => {
    const navigate = useNavigate();
    return (
        <Flex align="center" justify="center" vertical gap="middle" className="min-h-screen">
            <Typography.Title underline={true}>
                {props.data.username}
            </Typography.Title>

            <Typography.Text style={{fontSize: '20px'}}>
                {props.data.name} {props.data.surname}
            </Typography.Text>

            <Typography.Text>
                {props.data.email}
            </Typography.Text>

            <Button type="primary" hidden={!props.isMyAccount} onClick={() => navigate("/me/edit")}>
                Редактировать
            </Button>
        </Flex>
    )
}

export default UserViewFormComponent;