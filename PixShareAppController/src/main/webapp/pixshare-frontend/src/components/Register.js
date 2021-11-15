import React from "react";
import {Link} from "react-router-dom";
import {Button, Card, Form} from "react-bootstrap";
import axios from "axios";

export default class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.userRegister = this.userRegister.bind(this);
        this.userChange = this.userChange.bind(this);
    }

    initialState = {
        email:'', password:'',firstName:'', lastName:''
    }

    userRegister = event => {
        event.preventDefault();

        const user = new FormData();
        user.append("firstName", this.state.firstName);
        user.append("lastName", this.state.lastName);
        user.append("email", this.state.email);
        user.append("password", this.state.password);


        axios
            .post("http://localhost:8090/pix-share/mvc/user/add",user, {
                headers: { 'Content-Type': 'application/json' },
            })
            .then(response => {
                if(response.data != null){
                    this.setState(() => this.initialState);
                    // alert("User registered successfully")
                    if(!alert("User registered successfully")){
                        window.location ="/Login";
                    }
                    ;
                }
            })
            .catch(err => {
                alert("User could not be registered");
            });
    }

    userChange = event => {
        console.log(event.target.name);
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render() {
        const {email, firstName, lastName, password} = this.state;
        return (
            <Card className="loginCard">
                <Card.Header>
                    Login
                </Card.Header>
                <Form id="LoginForm" onSubmit={this.userRegister}>
                    <Card.Body>
                        <Form.Group controlId="formEmail">
                            <Form.Label>Email</Form.Label>
                            <Form.Control
                                required
                                type="text"
                                name="email"
                                value={email}
                                onChange={this.userChange}
                                placeholder="Enter email" />
                        </Form.Group>
                        <Form.Group controlId="formFirstName">
                            <Form.Label>First Name</Form.Label>
                            <Form.Control
                                required
                                type="text"
                                name="firstName"
                                value={firstName}
                                onChange={this.userChange}
                                placeholder="Enter first name" />
                        </Form.Group>
                        <Form.Group controlId="formLastName">
                            <Form.Label>Last Name</Form.Label>
                            <Form.Control
                                required
                                type="text"
                                name="lastName"
                                value={lastName}
                                onChange={this.userChange}
                                placeholder="Enter last name" />
                        </Form.Group>
                        <Form.Group controlId="formPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control
                                required
                                type="password"
                                name="password"
                                value={password}
                                onChange={this.userChange}
                                placeholder="Enter password" />
                        </Form.Group>

                    </Card.Body>
                    <Card.Footer style={{"textAlign":"right"}}>
                        <Button
                            variant="outline-light"
                            type="submit">
                            Register
                        </Button>{' '}
                    </Card.Footer>
                </Form>
            </Card>

        );
    }
}