import {MessageModel} from "../models/message/MessageModel.ts";
import {Button, Typography} from "antd";
import MessageComponent from "./MessageComponent.tsx";
import TextArea from "antd/es/input/TextArea";
import {SendOutlined} from "@ant-design/icons";

interface ChatComponentProps {
    messages: MessageModel[];
    myUsername: string
}

const ChatComponent = (props: ChatComponentProps) => {
    // const messagesEndRef = useRef<HTMLDivElement>();
    // const scrollToBottom = () => {
    //     if (messagesEndRef.current) {
    //         messagesEndRef.current.scrollTop = messagesEndRef.current.scrollHeight;
    //     }
    // }
    // // useEffect(() => {
    // //     scrollToBottom();
    // // }, []);

    return (
        <div className="flex items-center flex-col min-h-screen max-h-screen justify-between mx-3">
            <Typography.Title>Чат</Typography.Title>
            <div
                className="flex-1 bg-[#f5f5f5] max-w-[700px] w-full p-2 flex flex-col justify-between gap-6 pb-6 max-h-full overflow-hidden">
                <div className="flex gap-2.5 flex-col-reverse overflow-y-auto flex-1">
                    {props.messages.map((message, index) => (
                        <MessageComponent message={message} isMyMessage={false} key={index}/>
                    ))}

                    <Button type="dashed"
                            className="ml-auto mr-auto">
                        Больше
                    </Button>
                </div>
                <div className="flex">
                    <TextArea
                        // value={value}
                        // onChange={(e) => setValue(e.target.value)}
                        placeholder="Введите сообщение"
                        autoSize={{minRows: 2, maxRows: 3}}
                        maxLength={100}
                        showCount
                    />
                    <Button type="primary"
                            className="mt-auto mb-auto ml-1.5">
                        <SendOutlined/>
                    </Button>
                </div>
            </div>
        </div>
    )
}

export default ChatComponent;