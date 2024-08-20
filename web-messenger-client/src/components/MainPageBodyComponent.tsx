import ReactEmojis from "@souhaildev/reactemojis";
import {Button} from "antd";
import {useNavigate} from "react-router-dom";

const MainPageBodyComponent = () => {
    const navigation = useNavigate();
    return (
        <div className="flex flex-col items-center mt-28">
            <div className="flex flex-wrap text-justify lg:flex-row lg:justify-center items-center flex-col">
                <h1 className="lg:text-[38px] text-[20px] font-semibold">Добро пожаловать!</h1>
                <ReactEmojis emoji="👋" style={{width: 50, height: 50}}/>
            </div>

            <div className="flex flex-wrap text-justify lg:flex-row lg:justify-center items-center flex-col">
                <h1 className="lg:text-[38px] text-[20px] font-semibold">Место, где встречаются идеи и люди!</h1>
                <ReactEmojis emoji="⭐" style={{width: 50, height: 50}}/>
            </div>

            <Button type="primary" size="large" className="mt-14"
                    onClick={() => navigation('/registration')}>Зарегистрироваться</Button>

            <Button type="dashed" size="large" className="mt-14"
                    onClick={() => navigation('/chat')}>Открыть чат</Button>
        </div>
    )
}

export default MainPageBodyComponent;