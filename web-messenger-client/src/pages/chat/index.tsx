import ChatComponent from "../../components/ChatComponent.tsx";
import {useEffect, useState} from "react";
import {MessageModel} from "../../models/message/MessageModel.ts";
import GetMessagesRequestApi from "../../api/message/GetMessages.ts";
import {useNavigate} from "react-router-dom";
import LoaderComponent from "../../components/LoaderComponent.tsx";
import getMyUserRequest from "../../api/user/GetMyUserRequest.ts";
import {UserModel} from "../../models/user/UserModel.ts";

const ChatPage = () => {
    const navigate = useNavigate();
    const [loading, setLoading] = useState({myInfo: true, messages: true});
    const [messages, setMessages] = useState<MessageModel[]>([]);
    const [myUsername, setMyUsername] = useState<string>("");
    const [page, setPage] = useState<number>(1);
    const [size] = useState<number>(9);

    const [moreLoading, setMoreLoading] = useState(false);
    const [isHaveMore, setIsHaveMore] = useState(true);

    useEffect(() => {
        const getMessages = async () => {
            setMoreLoading(true);
            const response = await GetMessagesRequestApi(page, size);
            if (response.ok) {
                const data: MessageModel[] = await response.json();
                setMessages(prevState => [...prevState, ...data]);
                data.length < size ? setIsHaveMore(false) : setIsHaveMore(true);
                setMoreLoading(false);
            } else {
                navigate('/login');
            }
        }

        getMessages()
            .then(() => setTimeout(() =>
                setLoading(prevState => ({...prevState, messages: false})), 500))
            .catch(() => console.log('Ошибка в выполнении запроса получения сообщений в эффекте'))
    }, [navigate, page, size]);

    useEffect(() => {
        const getMyInfo = async () => {
            const response = await getMyUserRequest();
            if (response.ok) {
                const data: UserModel = await response.json();
                setMyUsername(data.username);
            } else {
                navigate('/login');
            }
        }

        getMyInfo()
            .then(() => setTimeout(() => setLoading(prevState => ({...prevState, myInfo: false})), 500))
            .catch(() => console.log('Ошибка в выполнении запроса получении пользователя в эффекте'))
    }, [navigate]);

    if (loading.messages || loading.myInfo) {
        return (<LoaderComponent/>)
    }

    return (
        <ChatComponent
            messages={messages}
            myUsername={myUsername}
            setPage={setPage}
            moreLoading={moreLoading}
            isHaveMore={isHaveMore}
        />
    )
}

export default ChatPage;