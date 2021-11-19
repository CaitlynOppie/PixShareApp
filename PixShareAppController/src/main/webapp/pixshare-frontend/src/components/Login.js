import React from "react";
import {Link} from "react-router-dom";
import Register from "./Register";
import {Button, Card, Form} from "react-bootstrap";
import axios from "axios";

export default class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        localStorage.setItem('loggedIn', 'Log In');
    }

    initialState = {
        id:'', exists:'', email:''
    }

    userChange = event => {
        console.log(event.target.name);
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    userLogin = event => {
        event.preventDefault();
        localStorage.setItem('email',this.state.email);
        if(localStorage.getItem('email') === ''){
            alert("Please enter your email to proceed");
        }else{
            console.log(localStorage.getItem('email'));
            axios
                .get("http://localhost:8090/pix-share/mvc/user/userID/"+ localStorage.getItem('email'))
                .then(res => res.data)
                .then((data) => {
                    console.log(data);
                    this.setState({id: data.data});
                    localStorage.setItem('userID',this.state.id);
                    axios
                        .get("http://localhost:8090/pix-share/mvc/user/exists/"+ localStorage.getItem('userID'))
                        .then(res => res.data)
                        .then((data) => {
                            console.log(data);
                            this.setState({exists: data.data});
                            if(this.state.exists){
                                localStorage.setItem('loggedIn', 'Log out');
                                window.location ="/MyImages";
                            }
                            else{
                                localStorage.setItem('loggedIn', 'Log in');
                                alert("User with email: " + localStorage.getItem('email') + " does not exist");
                                this.state.id = '';
                                this.state.exists='';
                            }
                        })
                        .catch(err => {
                            alert("User with email: " + localStorage.getItem('email') + " does not exist");
                        });

                });
        }
    }

    //userLogin = event => {
    //         event.preventDefault();
    //         localStorage.setItem('userID',this.state.id);
    //         axios
    //             .get("http://localhost:8090/pix-share/mvc/user/exists/"+ localStorage.getItem('userID'))
    //             .then(res => res.data)
    //             .then((data) => {
    //                 console.log(data);
    //                 this.setState({exists: data.data});
    //                 if(this.state.exists){
    //                     window.location ="/MyImages";
    //                 }
    //                 else{
    //                     alert("User with ID: " + localStorage.getItem('userID') + " does not exist");
    //                     this.state.id = '';
    //                     this.state.exists='';
    //                 }
    //             });
    //     }

    render() {
        localStorage.setItem('loggedIn', 'Log in');
        localStorage.setItem('email', '');
        const {id, email} = this.state;
        return (
            <Card className="loginCard">
                <Card.Header>
                    Log in
                </Card.Header>
                <Form id="LoginForm">
                    <Card.Body>
                        <Form.Group controlId="formEmail">
                            <Form.Label>Email</Form.Label>
                            <Form.Control
                                type="text"
                                name="email"
                                value={email}
                                onChange={this.userChange}
                                placeholder="example@gmail.com" />
                        </Form.Group>

                    </Card.Body>
                    <Card.Footer style={{"textAlign":"right"}}>
                        <Link
                            to={"Register"}
                            className="btn btn-outline-success">
                            <i className="fa fa-user-plus" aria-hidden="true"></i>
                            {' '}
                            Register
                        </Link>
                        {' '}
                        <Button
                            variant="outline-light"
                            onClick={this.userLogin}>
                            <i className="fa fa-user" aria-hidden="true"></i>
                            {' '}
                            Log in
                        </Button>{' '}

                    </Card.Footer>
                </Form>
            </Card>

        );
    }
}