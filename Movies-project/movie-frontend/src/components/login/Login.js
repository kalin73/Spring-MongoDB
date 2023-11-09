import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import api from '../../api/axiosConfig';
import './Login.css';

function Login() {

    const history = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    async function submit(e) {
        e.preventDefault();

        try {
            await api.post('api/v1/auth/login', {email, password})
                .then(res => {
                    if (res.data === 'exists') {
                        history("/", {state: {id: email}})
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
                    <label>Email</label>
                    <input
                        type="email"
                        onChange={(e) => {
                            setEmail(e.target.value)
                        }}
                        placeholder="Email"
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