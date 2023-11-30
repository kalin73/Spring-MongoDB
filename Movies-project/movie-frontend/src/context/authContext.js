import axios from "axios";
import {createContext, useEffect, useState} from "react";
import api from "../api/axiosConfig";

export const AuthContext = createContext();

export const AuthContextProvider = ({children}) => {
    let user = {};

    api.get("api/v1/loggedUser")
        .then(res => {
            setCurrentUser(res.data);
        })

    const [currentUser, setCurrentUser] = useState({});


    const login = async (inputs) => {
        const res = await api.post('api/v1/auth/login', inputs);
        setCurrentUser(res.data);

        console.log(currentUser);
    };

    const logout = async (inputs) => {
        await axios.post("/auth/logout");
        setCurrentUser(null);
    };

    // useEffect(() => {
    //     localStorage.setItem("user", JSON.stringify(currentUser));
    // }, [currentUser]);

    return (
        <AuthContext.Provider value={{currentUser, login, logout}}>
            {children}
        </AuthContext.Provider>
    );
};