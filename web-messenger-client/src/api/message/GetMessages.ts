const GetMessagesRequestApi = async (page: number, size: number, date: Date) => {
    const token = localStorage.getItem("token");
    return fetch(`/api/v1/messages?page=${page}&size=${size}&dateInMs=${date.getTime()}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    });
}

export default GetMessagesRequestApi;