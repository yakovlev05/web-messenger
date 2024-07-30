import ChatComponent from "../../components/ChatComponent.tsx";
import React, {useEffect, useState} from "react";
import {MessageModel} from "../../models/message/MessageModel.ts";
import GetMessagesRequestApi from "../../api/message/GetMessages.ts";
import {useNavigate} from "react-router-dom";
import LoaderComponent from "../../components/LoaderComponent.tsx";
import getMyUserRequest from "../../api/user/GetMyUserRequest.ts";
import {UserModel} from "../../models/user/UserModel.ts";

// Спасибо этому бро https://www.youtube.com/watch?v=fzYmsQvjzhg
// @ts-ignore
// import SockJS from 'sockjs-client/dist/sockjs'; // https://www.npmjs.com/package/sockjs-client && https://www.npmjs.com/package/@types/sockjs-client
// import {over} from 'stompjs'; // https://www.npmjs.com/package/stompjs && https://www.npmjs.com/package/@types/stompjs
import Stomp from 'stompjs';
import {message} from "antd";
import {MessageRequest} from "../../models/message/MessageRequest.ts";

const ChatPage = () => {
    const navigate = useNavigate();
    const [loading, setLoading] =
        useState({myInfo: true, messages: true, stompClient: true});
    const [messages, setMessages] = useState<MessageModel[]>([]);
    const [myUsername, setMyUsername] = useState<string>("");
    const [page, setPage] = useState<number>(1);
    const [size] = useState<number>(9);
    const [date] = useState<Date>(new Date());

    const [moreLoading, setMoreLoading] = useState(false);
    const [isHaveMore, setIsHaveMore] = useState(true);

    // Вебсокеты 🤬
    const [stompClient, setStompClient] = useState<Stomp.Client>();

    const [messageApi, contextHolder] = message.useMessage();

    useEffect(() => {
        const getMessages = async () => {
            setMoreLoading(true);
            const response = await GetMessagesRequestApi(page, size, date);
            if (response.ok) {
                const data: MessageModel[] = await response.json();
                const newMessages = data
                    .filter(x => !messages
                        .some(y => y.id === x.id))
                setMessages(prevState => [...prevState, ...newMessages]);
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

    useEffect(() => {
        const websocket = () => {
            // const socket = new SockJS(`/ws?token=${localStorage.getItem('token')}`);
            const socket = new WebSocket(`/ws?token=${localStorage.getItem('token')}`);
            const stompClient = Stomp.over(socket);

            stompClient.connect({
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }, () => {
                stompClient.subscribe('/topic/messages', (message) => {
                    if (message.body) {
                        setMessages(prevState => [JSON.parse(message.body), ...prevState]);
                    }
                });
                setStompClient(stompClient);

                setTimeout(() => setLoading(prevState => ({...prevState, stompClient: false})), 500);

            }, (error) => {
                console.log("Ошибка при подключении " + error);
                messageApi.error('Ошибка при подключении к серверу').then();
            });
        }

        websocket();
    }, [messageApi]);

    const sendMessage = (text: string, setSendLoading: React.Dispatch<React.SetStateAction<boolean>>) => {
        setSendLoading(true);
        const body: MessageRequest = {message: text};

        if (stompClient) {
            stompClient.send("/app/message", {
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }, JSON.stringify(body));
        } else {
            messageApi.error('Ошибка при отправке сообщения (undefined)').then();
        }

        setSendLoading(false);
    }

    if (loading.messages || loading.myInfo || loading.stompClient) {
        return (
            <>
                {contextHolder}
                <LoaderComponent/>
            </>
        )
    }

    return (
        <>
            {contextHolder}
            <ChatComponent
                messages={messages}
                myUsername={myUsername}
                setPage={setPage}
                moreLoading={moreLoading}
                isHaveMore={isHaveMore}
                onSendButton={sendMessage}
            />
        </>
    )
}

export default ChatPage;