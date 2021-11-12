import React from 'react';
import {Link} from 'react-router-dom';

import {Navbar, Nav} from 'react-bootstrap'

export default class NavigationBar extends React.Component{
    render(){
        return(
            <Navbar bg="dark" variant="dark">
                <Nav className="container-fluid">
                    <Nav.Item>
                        <Link to={""} className="navbar-brand">
                            <img
                                src="https://upload.wikimedia.org/wikipedia/commons/2/2e/Camera_Flat_Icon_Vector.svg"
                                width="40"
                                height="40"
                                alt="brand"
                            />{" "}
                            PixShare
                        </Link>
                     </Nav.Item>
                     <Nav.Item className="ml-auto">
                         <Link to={"/"} className="btn btn-outline-light">Log in</Link>
                     </Nav.Item>
                 </Nav>
            </Navbar>
        );
    }
}

