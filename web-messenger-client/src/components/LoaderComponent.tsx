import {Flex, Spin} from "antd";

const LoaderComponent = () => {
    return (
        <Flex align="center" gap="middle" justify="center" className="min-h-screen">
            <Spin size="large"/>
        </Flex>
    )
}

export default LoaderComponent;