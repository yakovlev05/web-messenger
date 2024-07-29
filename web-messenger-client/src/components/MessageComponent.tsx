import {MessageModel} from "../models/message/MessageModel.ts";
import {Button, Card} from "antd";
import {useNavigate} from "react-router-dom";

interface MessageComponentProps {
    message: MessageModel;
    isMyMessage: boolean
}

const MessageComponent = (props: MessageComponentProps) => {
    const navigate = useNavigate();
    return (
        <Card
            title={<Button
                type="link"
                onClick={() => navigate(`/${props.message.sender.username}`)}>
                {props.message.sender.username}</Button>}
            styles={props.isMyMessage ? {header: {textAlign: 'right'}} : {header: {textAlign: 'left'}}}
            className={props.isMyMessage ? "ml-auto" : "mr-auto"}
            bordered={true}
            style={{width: 300}}
            size={"small"} type="inner">
            <p style={{
                // whiteSpace: 'nowrap',
                // overflow: 'hidden',
                // textOverflow: 'ellipsis'
                textAlign: 'justify'
            }}>{props.message.message}</p>
        </Card>
    )
}

export default MessageComponent;