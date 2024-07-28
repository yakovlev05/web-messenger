const GetUserRequest = async (username: string) => {
    return fetch(`/api/v1/users/${username}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
}

export default GetUserRequest;