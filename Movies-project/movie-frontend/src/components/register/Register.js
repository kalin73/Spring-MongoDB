import React, {useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import api from '../../api/axiosConfig';
import './Register.css';

const Register = () => {

    const history = useNavigate();
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    async function submit(e){
        e.preventDefault();

        try{
            await api.post("api/v1/auth/register", {username, email, password})
                .then(res => {
                    if(res.data === 'exists'){
                        alert('User already exists');
                    }
                    else{
                        alert('Register complete!');
                        history("/login", {state: {id: email}})
                    }
                })
                .catch(e => {
                    alert("Wrong details");
                    console.log(e);
                })
        }
        catch(e){
            console.log(e);
        }
    }


    return (
        <div className="registerForm">
            <div className="registerTitle">
                <h1>Register</h1>
            </div>
            <form action="POST">

                <div className="input-group">
                    <label>Username</label>
                    <input
                        type="text"
                        onChange={(e) => {setUsername(e.target.value)}}
                        placeholder="Username" />
                </div>

                <div className="input-group">
                    <label>Email</label>
                    <input
                        type="email"
                        onChange={(e) => {setEmail(e.target.value)}}
                        placeholder="Email" />
                </div>

                <div className="input-group">
                    <label>Password</label>
                    <input
                        type="password"
                        onChange={(e) => {setPassword(e.target.value)}}
                        placeholder="Password" />
                </div>

                <div className="input-group">
                    <input
                        type="submit"
                        onClick={submit} />
                </div>
            </form>
        </div>
    )
}

export default Register