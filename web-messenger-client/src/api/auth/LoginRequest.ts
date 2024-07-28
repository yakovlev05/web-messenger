import {LoginRequest} from "../../models/auth/LoginRequest.ts";

const LoginRequestApi = async (data: LoginRequest) => {
    return await fetch('/api/v1/auth/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

export default LoginRequestApi;