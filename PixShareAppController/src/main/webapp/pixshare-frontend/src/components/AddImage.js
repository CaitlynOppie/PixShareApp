import React from 'react';
import {Button, Card, Form, ProgressBar} from "react-bootstrap";
import axios from 'axios';

export default class AddImage extends React.Component{


    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.imageChange = this.imageChange.bind(this);
        this.submitImage = this.submitImage.bind(this);
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
        img.append("file", this.state.selectedFile);


        axios
            .post("http://localhost:8090/pix-share/mvc/image/upload",img, {
            headers: { 'Content-Type': 'multipart/form-data' },
            })
            .then(response => {
                if(response.data != null){
                    this.setState(() => this.initialState);
                    if(!alert("Image saved successfully")){
                        window.location ="/";
                    }
                    ;
                }
            })
            .catch(err => {
            alert("Image could not be uploaded. Please try again, if the problem persists your image my be too large to upload");
            });
    }

    imageChange = event =>{
        console.log(event.target.name);
        if(event.target.name === "selectedFile"){
            this.state.selectedFile = event.target.files[0];
        }else{
            this.setState({
                [event.target.name]:event.target.value
            });
        }
    }

    render(){
        const {userID, selectedFile} = this.state;
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
                                    value={userID}
                                    onChange={this.imageChange}
                                    placeholder="Enter user ID" />
                            </Form.Group>
                        <Form.Group controlId="formImage">
                            <Form.Label>Image</Form.Label>
                            <Form.Control
                                type="file"
                                name="selectedFile"
                                value={selectedFile}
                                onChange={this.imageChange}
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