import React from 'react';

import {Navbar, Nav, Container} from 'react-bootstrap'

class NavigationBar extends React.Component{
    render(){
        return(
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand href="/">
                    <img
                        src="https://upload.wikimedia.org/wikipedia/commons/2/2e/Camera_Flat_Icon_Vector.svg"
                        width="40"
                        height="40"
                        alt="brand"
                    />{" "}
                    PixShare
                </Navbar.Brand>
                <Nav className="mr-auto">
                    <Nav.Link href="#">Add Image</Nav.Link>
                    <Nav.Link href="#">Shared Images</Nav.Link>
                    <Nav.Link href="#">Log out</Nav.Link>
                </Nav>
            </Navbar>
        );
    }
}

export default NavigationBar;