import axios from "axios";
import {createContext, useEffect, useState} from "react";
import api from "../api/axiosConfig";

export const AuthContext = createContext();

export const AuthContexProvider = ({children}) => {
    let user = {};

    api.get("api/v1/loggedUser")
        .then(res => {
            user = res.data;
        })
    const [currentUser, setCurrentUser] = useState(user);

    const login = async (email, password) => {
        const res = await axios.post('api/v1/auth/login', email, password);
        setCurrentUser(res.data);
    };

    const logout = async (inputs) => {
        await axios.post("/auth/logout");
        setCurrentUser(null);
    };

    useEffect(() => {
        localStorage.setItem("user", JSON.stringify(currentUser));
    }, [currentUser]);

    return (
        <AuthContext.Provider value={{currentUser, login, logout}}>
            {children}
        </AuthContext.Provider>
    );
};