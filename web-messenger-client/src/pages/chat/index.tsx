import ChatComponent from "../../components/ChatComponent.tsx";
import {useEffect, useState} from "react";
import {MessageModel} from "../../models/message/MessageModel.ts";
import GetMessagesRequestApi from "../../api/message/GetMessages.ts";
import {useNavigate} from "react-router-dom";
import LoaderComponent from "../../components/LoaderComponent.tsx";

const ChatPage = () => {
    const navigate = useNavigate();
    const [loading, setLoading] = useState(true);
    const [messages, setMessages] = useState<MessageModel[]>([]);

    useEffect(() => {
        const fetchData = async () => {
            const response = await GetMessagesRequestApi(1, 15);
            if (response.ok) {
                const data: MessageModel[] = await response.json();
                setMessages(prevState => [...prevState, ...data]);
            } else if (response.status === 403 || response.status === 401) {
                console.log("Ошибка авторизации");
                navigate('/login');
            } else {
                console.log("Ошибка при загрузке сообщений " + response);
            }
        }

        fetchData()
            .then(() => setTimeout(() => setLoading(false), 500))
            .catch(() => console.log("Ошибка выполнении функции в эффекте"))
    }, [navigate]);

    if (loading) {
        return (<LoaderComponent/>)
    }

    return (
        <ChatComponent messages={messages} myUsername={"ssdddv"}/>
    )
}

export default ChatPage;