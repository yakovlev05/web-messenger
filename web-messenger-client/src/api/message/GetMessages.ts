const GetMessagesRequestApi = async (page: number, size: number) => {
    const token = localStorage.getItem("token");
    return fetch(`/api/v1/messages?page=${page}&size=${size}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    });
}

export default GetMessagesRequestApi;