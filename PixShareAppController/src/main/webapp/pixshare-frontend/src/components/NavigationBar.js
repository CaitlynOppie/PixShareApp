import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {Container, Form, FormControl, NavDropdown} from 'react-bootstrap'

import {Navbar, Nav, Button} from 'react-bootstrap'

export default class NavigationBar extends React.Component{

    constructor(props) {
        super(props);
        this.userImages = this.userImages.bind(this);
        this.userLogin = this.userLogin.bind(this);
    }


    userLogin = event => {
        localStorage.setItem('email','');
        localStorage.setItem('userID','');
        window.location ="/Login";
    }

    userImages = event => {
        // if(localStorage.getItem('userID')){
        //     alert("You are not logged in, please log in to view and upload images");
        // }else{
            window.location ="/MyImages";
        // }

    }

    render(){
        return(
             <Navbar bg="dark" variant="dark" collapseOnSelect expand='sm'>
                    <Container>
                        <Nav.Item className='fluid'>
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
                        <Navbar.Toggle aria-controls='responsive-navbar-nav'/>
                        <Navbar.Collapse id='responsive-navbar-nav'>
                            <div className="ms-auto">
                                <Nav.Item>
                                    <p className="userEmail">{localStorage.getItem('email')}</p>
                                    <Button
                                        variant="outline-light"
                                        onClick={this.userImages}>
                                        <i className="fa fa-picture-o" aria-hidden="true"></i>
                                        {' '}
                                        Images
                                    </Button>
                                    {' '}
                                    <Button
                                        variant="outline-light"
                                        onClick={this.userLogin}>
                                        <i className="fa fa-user" aria-hidden="true"></i>
                                        {' '}
                                        {localStorage.getItem('loggedIn')}
                                    </Button>
                                    <p className="userEmail"></p>
                                </Nav.Item>
                            </div>
                        </Navbar.Collapse>
                    </Container>
            </Navbar>

        // <Navbar collapseOnSelect fixed='top' expand='sm' variant='dark'>
        //     <Container>
        //         <Navbar.Toggle aria-controls='responsive-navbar-nav'/>
        //         <Navbar.Collapse id='responsive-navbar-nav'>
        //             <Nav>
        //                 <Nav.Link>Home</Nav.Link>
        //                 <Nav.Link>Home</Nav.Link>
        //                 <Nav.Link>Home</Nav.Link>
        //                 <Nav.Link>Home</Nav.Link>
        //             </Nav>
        //         </Navbar.Collapse>
        //     </Container>
        // </Navbar>

        );
    }
}



