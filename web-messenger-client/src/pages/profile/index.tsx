import {useNavigate, useParams} from "react-router-dom";
import {UserModel} from "../../models/user/UserModel.ts";
import UserViewFormComponent from "../../components/UserViewFormComponent.tsx";
import {useEffect, useState} from "react";
import GetMyUserRequest from "../../api/user/GetMyUserRequest.ts";
import LoaderComponent from "../../components/LoaderComponent.tsx";
import GetUserRequest from "../../api/user/GetUserRequest.ts";
import FloatChatButtonComponent from "../../components/FloatChatButtonComponent.tsx";

const ProfilePage = () => {
    const navigate = useNavigate();
    const {username} = useParams();
    const [loading, setLoading] = useState<boolean>(true);
    const [data, setData] =
        useState<UserModel>({
            email: '',
            name: '',
            username: '',
            surname: ''
        });
    const [isMyAccount, setIsMyAccount] = useState<boolean>(false);

    useEffect(() => {
        const fetchData = async () => {
            if (!username || username === 'me') {
                const response = await GetMyUserRequest();
                if (response.ok) {
                    const data: UserModel = await response.json();
                    setData(data);
                    setIsMyAccount(true);
                } else {
                    navigate('/login')
                }
            } else {
                const response = await GetUserRequest(username);
                if (response.ok) {
                    const data: UserModel = await response.json();
                    setData(data);
                } else {
                    navigate('/me')
                }

                const response2 = await GetMyUserRequest();
                if (response2.ok) {
                    const data: UserModel = await response2.json();
                    if (data.username === username) {
                        setIsMyAccount(true);
                    }
                }
            }
        }

        fetchData()
            .then(() => setTimeout(() => setLoading(false), 500))
            .catch(() => console.log("Ошибка при загрузке данных пользователя"));
    }, [navigate, username]);

    if (loading) {
        return (
            <LoaderComponent/>
        )
    }

    return (
        <>
            <UserViewFormComponent data={data} isMyAccount={isMyAccount}/>
            <FloatChatButtonComponent/>
        </>
    )
}

export default ProfilePage;