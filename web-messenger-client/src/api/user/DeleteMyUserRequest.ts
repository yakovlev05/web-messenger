const DeleteMyUserRequestApi = async () => {
    const token = localStorage.getItem("token");
    return fetch('/api/v1/users/me', {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    });
}

export default DeleteMyUserRequestApi;