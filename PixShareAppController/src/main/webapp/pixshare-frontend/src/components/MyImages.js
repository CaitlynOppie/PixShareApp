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
    }

    componentDidMount() {
        axios
            .get("http://localhost:8090/pix-share/mvc/image/viewAll/17")
            .then(res => res.data)
            .then((data) => {
                console.log(data);
                this.setState({images: data.data});
            });
        console.log(this.state.images);
    }

    deleteImage = (image) => {
        axios.delete("http://localhost:8090/pix-share/mvc/image/delete/" + image.userID + "/" + image.name)
            .then(response => {
                if (response.data != null) {
                    if (!alert(image.name + " deleted successfully")) {
                        window.location.reload();
                    }
                }
            })
        // alert("http://localhost:8090/pix-share/mvc/image/delete/17/"+image.name);
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

            const img = new FormData();
            img.append("sharedUserID", this.state.userID);
            // alert("http://localhost:8090/pix-share/mvc/sharedImage/shareImage/" + image.imageID + "/" + image.userID + "/" + this.state.userID + "/" + image.name);


            axios
                .post("http://localhost:8090/pix-share/mvc/sharedImage/shareImage/" + image.imageID + "/" + image.userID + "/" + this.state.userID + "/" + image.name)
                .then(response => {
                    alert("Image shared successfully");
                })
                .catch(err => {
                    alert("Image could not be shared. Double check userID");
                });
        }
    }


    render() {
        const {userID} = this.state;
        return (
            <Card className="cards">
                <Card.Header>
                    My Images
                    {' '}
                    <Link to={"AddImage"} className="btn btn-outline-light">Add Image</Link>
                </Card.Header>
                <Card.Body>
                    <div>
                        {this.state.images.map((image) => (
                            <div className="imageLayout">
                                <img
                                    className="image"
                                    key={image.size}
                                    src={"http://localhost:8090/pix-share/mvc/image/view/17/" + image.name}
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
                                    <i className="fa fa-download">

                                    </i> </Button>
                                {' '}
                                <Button
                                    variant="outline-danger"
                                    type="button"
                                    className="buttonLayout"
                                    onClick={this.deleteImage.bind(this, image)}>
                                    <i className="fa fa-trash"></i>
                                </Button>
                                <br/>
                                <Form onSubmit={this.shareImage(image)} id="ShareImageForm">
                                    <InputGroup className="mb-3">
                                        <FormControl
                                            placeholder="User ID"
                                            aria-label="User ID"
                                            aria-describedby="basic-addon2"
                                            name="userID"
                                            value={userID}
                                            onChange={this.setUserID}
                                        />
                                        <Button
                                            variant="outline-light"
                                            type="submit">
                                            <i className="fa fa-share-alt"></i>
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