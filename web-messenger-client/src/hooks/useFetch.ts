import {useEffect, useState} from "react";

interface QueryParams {
    [key: string]: string
}

interface FetchOptions {
    method: string;
    headers?: HeadersInit;
    body?: BodyInit
}

const useFetch = <T>(url: string, options: FetchOptions, queryParams?: QueryParams) => {
    const [loading, setLoading] = useState<boolean>(false);
    const [data, setData] = useState<T | null>(null);
    const [error, setError] = useState<Error | null>();

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);

            const queryString = queryParams ? '?' + new URLSearchParams(queryParams).toString() : '';
            const response = fetch(url + queryString, options);

            response
                .then(async r => setData((await r.json())))
                .catch(err => {
                    console.error(err)
                    setError(err)
                })
                .finally(() => setLoading(false));

        }

        fetchData()
            .then();
    }, [url, queryParams, options]);

    return {data, loading, error};
}

export default useFetch;