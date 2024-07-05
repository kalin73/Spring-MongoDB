import {createContext, useState} from "react";
import api from "../api/axiosConfig";

export const AuthContext = createContext();

export const AuthContextProvider = ({children}) => {
    const loggedUser = async () => {
        const res = await api.get("/api/v1/loggedUser");
        setCurrentUser(res.data);
    }

    const [currentUser, setCurrentUser] = useState(loggedUser);

    const login = async (inputs) => {
        const res = await api.post('api/v1/auth/login', inputs);
        setCurrentUser(res.data);

    };

    const logout = async () => {
        const res = await api.get("api/v1/auth/logout");
        setCurrentUser(res.data);
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