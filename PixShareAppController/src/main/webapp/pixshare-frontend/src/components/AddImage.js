import React from 'react';
import {Button, Card, Col, Container, Form, Row} from "react-bootstrap";
import {Link} from "react-router-dom";

export default class AddImage extends React.Component{

    constructor(props) {
        super(props);
        this.state ={userID:'', image:''};
        this.imageChange = this.imageChange.bind(this);
        this.submitImage = this.submitImage.bind(this);
    }

    submitImage(event){
        alert('UserID: '+ this.state.userID);
        event.preventDefault();
    }

    imageChange(event){
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render(){
        return(
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>
                    Add New Image
                </Card.Header>
                <Form onSubmit={this.submitImage} id="AddImageForm">
                    <Card.Body>
                            <Form.Group controlId="formUserID">
                                <Form.Label>User ID</Form.Label>
                                <Form.Control
                                    required
                                    type="userID"
                                    name="userID"
                                    value={this.state.userID}
                                    onChange={this.imageChange}
                                    placeholder="Enter user ID" />
                            </Form.Group>
                            <Form.Group controlId="formImage">
                                <Form.Label>Image</Form.Label>
                                <Form.Control
                                    required
                                    type="file"
                                    name="image"
                                    value={this.state.image}
                                    onChange={this.imageChange}
                                />
                            </Form.Group>
                    </Card.Body>
                <Card.Footer style={{"textAlign":"right"}}>
                    <Button
                        variant="outline-light"
                        type="submit">
                        Add Image
                    </Button>
                </Card.Footer>
                </Form>
            </Card>
        );
    }
}