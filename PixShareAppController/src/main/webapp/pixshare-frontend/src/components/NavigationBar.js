import React from 'react';
import {Link} from 'react-router-dom';

import {Navbar, Nav} from 'react-bootstrap'

export default class NavigationBar extends React.Component{
    render(){
        return(
            <Navbar bg="dark" variant="dark">
                <Link to={""} className="navbar-brand">
                    <img
                        src="https://upload.wikimedia.org/wikipedia/commons/2/2e/Camera_Flat_Icon_Vector.svg"
                        width="40"
                        height="40"
                        alt="brand"
                    />{" "}
                    PixShare
                </Link>
                <Nav className="mr-auto">
                    <Link to={"MyImages"} className="nav-link">My Images</Link>
                    <Link to={"SharedImages"} className="nav-link">Shared Images</Link>
                    {/*<Link href="#">Log out</Link>*/}
                </Nav>
            </Navbar>
        );
    }
}

