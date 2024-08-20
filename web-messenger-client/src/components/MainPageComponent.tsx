import MainPageHeaderComponent from "./MainPageHeaderComponent.tsx";
import MainPageBodyComponent from "./MainPageBodyComponent.tsx";
import {FloatButton} from "antd";
import {CommentOutlined} from "@ant-design/icons";

const MainPageComponent = () => {
    return (
        <>
            <MainPageHeaderComponent/>
            <MainPageBodyComponent/>
            <FloatButton
                shape="square"
                type="primary"
                icon={<CommentOutlined/>}
                description="Чат"
                href="/chat"
            />
        </>
    )
}


export default MainPageComponent;