import {UserModel} from "../../models/user/UserModel.ts";

const UpdateMyUserRequestApi = async (data: UserModel) => {
    const token = localStorage.getItem("token");
    return await fetch('/api/v1/users/me', {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(data)
    });
}

export default UpdateMyUserRequestApi;