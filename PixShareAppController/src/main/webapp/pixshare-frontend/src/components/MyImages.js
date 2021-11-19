import React from 'react';
import axios from 'axios';
import AddImage from "./AddImage";


import {Card, Container, Row, Col, Button, InputGroup, FormControl, Form} from 'react-bootstrap'
import {Link} from "react-router-dom";

export default class MyImages extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            images: []
        };
        this.state.exists = '';
        this.state.email = '';
        this.deleteImage = this.deleteImage.bind(this);
        this.downloadImage = this.downloadImage.bind(this);
        this.setUserID = this.setUserID.bind(this);
        this.shareImage = this.shareImage.bind(this);
        this.addImage = this.addImage.bind(this);
    }

    componentDidMount() {
        axios
            .get("http://localhost:8090/pix-share/mvc/image/viewAll/"+ localStorage.getItem('userID'))
            .then(res => res.data)
            .then((data) => {
                console.log(data);
                this.setState({images: data.data});
            });
        console.log(this.state.images);
    }

    deleteImage = (image) => {
        axios.delete("http://localhost:8090/pix-share/mvc/image/delete/" + localStorage.getItem('userID') + "/" + image.name)
            .then(response => {
                if (response.data != null) {
                    if (!alert(image.name + " deleted successfully")) {
                        window.location.reload();
                    }
                }
            })
    };

    downloadImage = (image) => {
        window.open("http://localhost:8090/pix-share/mvc/image/download/" + image.userID + "/" + image.name);
    }

    setUserID = event => {
        console.log(event.target.name);
        this.setState({
            [event.target.name]:event.target.value
        });
    }


    shareImage(image) {
        return event => {
            event.preventDefault();
            localStorage.setItem('sharedUserEmail', this.state.email);
            if(localStorage.getItem('sharedUserEmail') === ''){
                alert("Email of user needs to be provided in order to share image");
            }else{
                console.log(localStorage.getItem('sharedUserEmail'));
                axios
                    .get("http://localhost:8090/pix-share/mvc/user/userID/"+ localStorage.getItem('sharedUserEmail'))
                    .then(res => res.data)
                    .then((data) => {

                        localStorage.setItem('sharedUserID', data.data);
                        console.log(localStorage.getItem('sharedUserID'));
                    });
                axios
                    .get("http://localhost:8090/pix-share/mvc/user/exists/"+ localStorage.getItem('sharedUserID'))
                    .then(res => res.data)
                    .then((data) => {
                        console.log(data);
                        this.setState({exists: data.data});
                        if(this.state.exists){
                            const img = new FormData();
                            img.append("date", image.date);
                            img.append("imageid", image.imageID);
                            img.append("link", image.link);
                            img.append("name", image.name);
                            img.append("sharedUserID", localStorage.getItem('sharedUserID'));
                            img.append("size", image.size);
                            img.append("user", image.userID);

                            axios
                                .post("http://localhost:8090/pix-share/mvc/sharedImage/shareImage",img)
                                .then(response => {
                                    alert("Image shared successfully");
                                })
                                .catch(err => {
                                    alert("Image could not be shared. Double check userID");
                                });
                        }
                        else{
                            alert("User with ID: " + localStorage.getItem('sharedUserID') + " does not exist");
                            this.state.userID = '';
                            this.state.exists='';
                        }
                    });

                localStorage.setItem('sharedUserEmail', '');
                localStorage.setItem('sharedUserID', '');
            }
            this.state.email='';
        }
    }

    addImage = event => {
        if(localStorage.getItem('userID') === ''){
            alert("You are not logged in, please log in to start uploading images");
        }else{
            window.location ="/AddImage";
        }

    }


    render() {
        const {userID, email} = this.state;
        let uID = localStorage.getItem('userID');
        return (
            <Card className="cards">
                <Card.Header>
                    My Images
                    {' '}
                    <Button
                        variant="outline-light"
                        type="submit"
                        className="buttonLayout"
                        onClick={this.addImage}>
                        <i className="fa fa-plus" aria-hidden="true"></i>
                        {' '}
                        Add Image
                    </Button>
                </Card.Header>
                <Card.Body>
                    <div>
                        {this.state.images.map((image) => (
                            <div className="imageLayout">
                                <img
                                    className="image"
                                    key={image.imageID}
                                    src={"http://localhost:8090/pix-share/mvc/image/view/"+ uID + "/" + image.name}
                                />
                                {' '}
                                <br/>
                                <div className="metadata">{image.name}</div>
                                <div className="metadata">Size: {image.size}KB</div>
                                <div className="metadata">Date: {image.date}</div>


                                {' '}
                                <Button
                                    variant="outline-success"
                                    type="button"
                                    className="buttonLayout"
                                    onClick={this.downloadImage.bind(this, image)}>
                                    <i className="fa fa-download"></i>
                                    {' '}
                                    Download
                                </Button>
                                {' '}
                                <Button
                                    variant="outline-danger"
                                    type="button"
                                    className="buttonLayout"
                                    onClick={this.deleteImage.bind(this, image)}>
                                    <i className="fa fa-trash"></i>
                                    {' '}
                                    Delete
                                </Button>
                                <br/>
                                <Form onSubmit={this.shareImage(image)} id="ShareImageForm">
                                    <InputGroup className="mb-3">
                                        <FormControl
                                            placeholder="Email"
                                            aria-label="Email"
                                            aria-describedby="basic-addon2"
                                            name="email"
                                            value={email}
                                            onChange={this.setUserID}
                                        />
                                        <Button
                                            variant="outline-light"
                                            type="submit">
                                            <i className="fa fa-share-alt"></i>
                                            {' '}
                                            Share
                                        </Button>
                                    </InputGroup>
                                </Form>
                                <br/>
                            </div>
                        ))}
                    </div>
                </Card.Body>
            </Card>
        );
    }

}