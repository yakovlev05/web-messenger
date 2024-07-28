import {RegistrationRequest} from "../../models/auth/RegistrationRequest.ts";

const RegistrationRequestApi = async (data: RegistrationRequest) => {
    return await fetch("/api/v1/auth/registration", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

export default RegistrationRequestApi;