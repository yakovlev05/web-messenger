import {FloatButton} from "antd";
import {CommentOutlined} from "@ant-design/icons";

const FloatChatButtonComponent = () => {
    return (
        <FloatButton
            shape="square"
            type="primary"
            icon={<CommentOutlined/>}
            description="Чат"
            href="/chat"
        />
    )
}

export default FloatChatButtonComponent;