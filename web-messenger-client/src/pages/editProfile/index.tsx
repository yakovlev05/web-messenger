import UpdateUserFormComponent from "../../components/UpdateUserFormComponent.tsx";
import {UserModel} from "../../models/user/UserModel.ts";
import {useEffect, useState} from "react";
import LoaderComponent from "../../components/LoaderComponent.tsx";
import GetMyUserRequest from "../../api/user/GetMyUserRequest.ts";

const EditProfilePage = () => {
    const [data, setData] = useState<UserModel>({
        name: '',
        surname: '',
        email: '',
        username: ''
    });
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        const fetchData = async () => {
            const response = await GetMyUserRequest();
            if (response.ok) {
                const json = await response.json();
                setData(json);
            }
        }
        fetchData()
            .then(() => setTimeout(() => setLoading(false), 500));
    }, []);

    if (loading) {
        return (<LoaderComponent/>)
    }

    return (
        <UpdateUserFormComponent data={data} setData={setData}/>
    )
}

export default EditProfilePage;