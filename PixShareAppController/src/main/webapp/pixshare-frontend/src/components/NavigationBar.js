import React, {Component} from 'react';
import {Link} from 'react-router-dom';

import {Navbar, Nav, Button} from 'react-bootstrap'

export default class NavigationBar extends React.Component{

    userLogin = event => {
        localStorage.setItem('email','');
        localStorage.setItem('userID','');
        window.location ="/Login";
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
                         <Link
                             to={"/MyImages"}
                             className="btn btn-outline-light">
                             <i className="fa fa-picture-o" aria-hidden="true"></i>
                             {' '}
                             Images
                         </Link>
                         {' '}
                         {/*<Link*/}
                         {/*    to={"/Login"}*/}
                         {/*    className="btn btn-outline-light">*/}
                         {/*    <i className="fa fa-user" aria-hidden="true"></i>*/}
                         {/*    {' '}*/}
                         {/*    Log in*/}
                         {/*</Link>*/}
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

