import React, {Component} from 'react';
import {Link} from 'react-router-dom';

import {Navbar, Nav, Button} from 'react-bootstrap'

export default class NavigationBar extends React.Component{

    userLogin = event => {
        localStorage.setItem('email','');
        localStorage.setItem('userID','');
        window.location ="/Login";
    }

    userImages = event => {
        if(localStorage.getItem('userID') === '' || localStorage.getItem('email') === ''){
            alert("You are not logged in, please log in to view and upload images");
        }else{
            window.location ="/MyImages";
        }

    }

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
                     <Nav.Item className="navbar-right">
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
                             Login
                         </Button>
                     </Nav.Item>
                 </Nav>
            </Navbar>
        );
    }
}

