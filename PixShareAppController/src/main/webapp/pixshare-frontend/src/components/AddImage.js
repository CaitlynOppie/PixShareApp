import React from 'react';
import {Button, Card, Form} from "react-bootstrap";
import axios from 'axios';

export default class AddImage extends React.Component{


    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.imageChange = this.imageChange.bind(this);
        this.submitImage = this.submitImage.bind(this);
        this.handleImageChange = this.handleImageChange.bind(this);
    }

    initialState = {
        userID:'', selectedFile:''
    }

    resetImage= event =>{
        this.setState(() => this.initialState);
    }

    submitImage= event =>{
        event.preventDefault();

        const img = new FormData();
        img.append("userID", this.state.userID);
        img.append("selectedFile", this.state.selectedFile);


        axios
            .post("http://localhost:8090/pix-share/mvc/image/upload",img, {
            headers: { 'Content-Type': 'multipart/form-data' },
            })
            .then(response => {
                if(response.data != null){
                    this.setState(() => this.initialState);
                    alert("Image saved successfully");
                }
            })
            .catch(err => {
            console.log(err)});
    }

    imageChange = event =>{
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    handleImageChange = event =>{
        // this.setState({
        //     selectedFile : event.target.files[0]
        // });
        console.log(event.target.files[0]);
    }

    render(){
        return(
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>
                    Add New Image
                </Card.Header>
                <Form onReset={this.resetImage} onSubmit={this.submitImage} id="AddImageForm">
                    <Card.Body>
                            <Form.Group controlId="formUserID">
                                <Form.Label>User ID</Form.Label>
                                <Form.Control
                                    required
                                    type="ID"
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
                                name="selectedFile"
                                value={this.state.image}
                                onChange={this.handleImageChange}
                            />
                        </Form.Group>
                    </Card.Body>
                <Card.Footer style={{"textAlign":"right"}}>
                    <Button
                        variant="outline-danger"
                        type="reset">
                        Reset Form
                    </Button>{' '}
                    <Button
                        variant="outline-light"
                        type="submit">
                        Add Image
                    </Button>{' '}
                </Card.Footer>
                </Form>
            </Card>
        );
    }
}