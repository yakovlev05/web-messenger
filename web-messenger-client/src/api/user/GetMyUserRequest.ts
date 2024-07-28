const GetMyUserRequest = async () => {
    const token = localStorage.getItem("token");
    return fetch('/api/v1/users/me', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
            'dvdffv': 'sdvddsdvdffdv'
        }
    });
}

export default GetMyUserRequest;