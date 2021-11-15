import React from "react";
import {Link} from "react-router-dom";
import Register from "./Register";
import {Button, Card, Form} from "react-bootstrap";
import axios from "axios";

export default class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
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
                            window.location ="/MyImages";
                        }
                        else{
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
        const {id, email} = this.state;
        return (
            <Card className="loginCard">
                <Card.Header>
                    Login
                </Card.Header>
                <Form id="LoginForm">
                    <Card.Body>
                        {/*<Form.Group controlId="formID">*/}
                        {/*    <Form.Label>User ID</Form.Label>*/}
                        {/*    <Form.Control*/}
                        {/*        required*/}
                        {/*        type="text"*/}
                        {/*        name="id"*/}
                        {/*        value={id}*/}
                        {/*        onChange={this.userChange}*/}
                        {/*        placeholder="Enter userID" />*/}
                        {/*</Form.Group>*/}
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

                    </Card.Body>
                    <Card.Footer style={{"textAlign":"right"}}>
                        <Button
                            variant="outline-light"
                            onClick={this.userLogin}>
                            Login
                        </Button>{' '}
                        <Link
                            to={"Register"}
                            className="btn btn-outline-success">
                            Register
                        </Link>
                    </Card.Footer>
                </Form>
            </Card>

        );
    }
}