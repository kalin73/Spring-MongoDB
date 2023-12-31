import React, {useContext} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faVideoSlash} from "@fortawesome/free-solid-svg-icons";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container"
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import {Link, NavLink} from "react-router-dom";
import {AuthContext} from "../../context/authContext";

const Header = () => {
    const {currentUser} = useContext(AuthContext);
    const {logout} = useContext(AuthContext);

    async function logoutFunc(e) {
        e.preventDefault();

        await logout();
    }


    return (
        <Navbar bg="dark" variant="dark" expand="lg">
            <Container fluid>
                <Navbar.Brand href="/" style={{"color": 'gold'}}>
                    <FontAwesomeIcon icon={faVideoSlash}/>Gold
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll"/>
                <Navbar.Collapse id="navbarScroll">
                    <Nav
                        className="me-auto my-2 my-lg-0"
                        style={{maxHeight: '100px'}}
                        navbarScroll
                    >
                        <NavLink className="nav-link" to="/">Home</NavLink>
                        <NavLink className="nav-link" to="/watchList">Watch List</NavLink>
                    </Nav>
                    <div>
                        {
                            currentUser.logged ?
                                <div>
                                    <Button variant="outline-info" className="me-2" onClick={logoutFunc}><Link
                                        to={"/"}>Logout</Link></Button>
                                </div>
                                :
                                <div>
                                    <Button variant="outline-info" className="me-2"><Link
                                        to={"./Login"}>Login</Link></Button>
                                    <Button variant="outline-info" className="registerBtn"><Link
                                        to={"./Register"}>Register</Link></Button>
                                </div>
                        }
                    </div>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default Header