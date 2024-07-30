import {MessageModel} from "../models/message/MessageModel.ts";
import {Button, Typography} from "antd";
import MessageComponent from "./MessageComponent.tsx";
import TextArea from "antd/es/input/TextArea";
import {SendOutlined} from "@ant-design/icons";
import React, {useState} from "react";

interface ChatComponentProps {
    messages: MessageModel[];
    myUsername: string
    setPage: React.Dispatch<React.SetStateAction<number>>;
    moreLoading: boolean;
    isHaveMore: boolean;
    onSendButton: (text: string, setLoading: React.Dispatch<React.SetStateAction<boolean>>) => void;
}

const ChatComponent = (props: ChatComponentProps) => {
    const [sendIsLoading, setSendIsLoading] = React.useState(false);
    const [textMessage, setTextMessage] = useState<string>("");

    const handlerSendButton = () => {
        if (textMessage.length === 0) return;
        props.onSendButton(textMessage, setSendIsLoading);
        setTextMessage('');
    }

    return (
        <div className="flex items-center flex-col min-h-screen max-h-screen justify-between mx-3">
            <Typography.Title>Чат</Typography.Title>
            <div
                className="flex-1 bg-[#f5f5f5] max-w-[700px] w-full p-2 flex flex-col justify-between gap-6 pb-6 max-h-full overflow-hidden">
                <div className="flex gap-2.5 flex-col-reverse overflow-y-auto flex-1">
                    {props.messages.map((message) => (
                        <MessageComponent message={message}
                                          isMyMessage={props.myUsername === message.sender.username}
                                          key={message.id}/>
                    ))}

                    <Button type="dashed"
                            className="ml-auto mr-auto"
                            onClick={() => props.setPage(prev => prev + 1)}
                            loading={props.moreLoading}
                            hidden={!props.isHaveMore}>
                        Больше
                    </Button>
                </div>
                <div className="flex">
                    <TextArea
                        // value={value}
                        // onChange={(e) => setValue(e.target.value)}
                        placeholder="Введите сообщение"
                        autoSize={{minRows: 2, maxRows: 5}}
                        maxLength={1000}
                        showCount
                        onInput={(e) => setTextMessage(e.currentTarget.value)}
                        value={textMessage}
                    />
                    <Button type="primary"
                            className="mt-auto mb-auto ml-1.5"
                            loading={sendIsLoading}
                            onClick={handlerSendButton}>
                        <SendOutlined/>
                    </Button>
                </div>
            </div>
        </div>
    )
}

export default ChatComponent;