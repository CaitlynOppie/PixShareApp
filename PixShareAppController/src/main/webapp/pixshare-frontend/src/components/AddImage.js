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
        userID:localStorage.getItem('userID'), selectedFile:''
    }

    resetImage= event =>{
        this.setState(() => this.initialState);
    }

    submitImage= event =>{
        event.preventDefault();

        const img = new FormData();
        img.append("userID",localStorage.getItem('userID'));
        img.append("file", this.state.selectedFile);


        axios
            .post("http://localhost:8090/pix-share/mvc/image/upload",img, {
            headers: { 'Content-Type': 'multipart/form-data' },
            })
            .then(response => {
                if(response.data != null){
                    this.setState(() => this.initialState);
                    if(!alert("Image saved successfully")){
                        window.location ="/MyImages";
                    }
                }
            })
            .catch(err => {
            alert("Image could not be uploaded. Please try again, if the problem persists your image my be too large to upload");
            });
    }

    imageChange = event =>{
        this.state.selectedFile = event.target.files[0];
    }

    render(){
        return(
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>
                    Add New Image
                </Card.Header>
                <Form onReset={this.resetImage} onSubmit={this.submitImage} id="AddImageForm">
                    <Card.Body>
                        <div>
                            <h5>Image</h5>
                            <input type="file" onChange={this.imageChange}/>
                        </div>
                    </Card.Body>
                <Card.Footer style={{"textAlign":"right"}}>
                    <Button
                        variant="outline-light"
                        type="submit"
                        className="buttonLayout">
                        <i className="fa fa-plus" aria-hidden="true"></i>
                        {' '}
                        Add Image
                    </Button>{' '}
                    <Button
                        variant="outline-danger"
                        type="reset"
                        className="buttonLayout">
                        <i className="fa fa-undo" aria-hidden="true"></i>
                        {' '}
                        Reset Form
                    </Button>{' '}

                </Card.Footer>
                </Form>
            </Card>
        );
    }
}