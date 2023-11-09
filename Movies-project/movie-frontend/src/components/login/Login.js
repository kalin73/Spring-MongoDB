import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import api from '../../api/axiosConfig';

function Login() {

    const history = useNavigate();
    const [user, setUsername] = useState('');
    const [password, setPassword] = useState('');

    async function submit(e) {
        e.preventDefault();

        try {
            await api.post('api/v1/auth/login', {user, password})
                .then(res => {
                    if (res.data === 'exists') {
                        history("/home", {state: {id: user}})
                    } else {
                        alert("User hove not register")
                    }
                })
                .catch(e => {
                    alert("Wrong details");
                    console.log(e);
                })
        } catch (e) {
            console.log(e);
        }
    }

    return (
        <div className="loginForm">

            <div className="loginTitle">
                <h1>Log In</h1>
            </div>

            <form action="POST">

                <div className="input-group">
                    <label>Username</label>
                    <input
                        type="text"
                        onChange={(e) => {
                            setUsername(e.target.value)
                        }}
                        placeholder="Username"
                    />
                </div>

                <div className="input-group">
                    <label>Password</label>
                    <input
                        type="password"
                        onChange={(e) => {
                            setPassword(e.target.value)
                        }}
                        placeholder="Password"
                    />
                </div>

                <div className="input-group">
                    <input
                        type="submit"
                        onClick={submit}/>
                </div>
            </form>
        </div>
    )
}

export default Login;