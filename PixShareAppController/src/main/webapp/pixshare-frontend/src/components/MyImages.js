import React from 'react';
import axios from 'axios';
import AddImage from "./AddImage";


import {Card, Container, Row, Col, Button, InputGroup, FormControl} from 'react-bootstrap'
import {Link} from "react-router-dom";

export default class MyImages extends React.Component{

    constructor(props) {
        super(props);
        this.state ={
            images : []
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
        axios.delete("http://localhost:8090/pix-share/mvc/image/delete/"+image.userID+"/"+image.name)
            .then(response => {
                if(response.data != null){
                    if(!alert(image.name + " deleted successfully")){
                        window.location.reload();
                    }
                }
            })
        // alert("http://localhost:8090/pix-share/mvc/image/delete/17/"+image.name);
    };

    downloadImage = (image) => {
        window.open("http://localhost:8090/pix-share/mvc/image/download/"+image.userID+"/"+image.name);
    }

    shareImage = (image) => {
        alert(image.userID)
    }

    render(){
        return(
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
                                <InputGroup className="mb-3">
                                    <FormControl
                                        placeholder="User ID"
                                        aria-label="User ID"
                                        aria-describedby="basic-addon2"
                                        value={this.state.userID}
                                    />
                                    <Button
                                        variant="outline-light"
                                        type="button"
                                        onClick={this.shareImage.bind(this, image, this.state.userID)}>
                                        <i className="fa fa-share-alt"></i>
                                    </Button>
                                </InputGroup>
                                <br/>
                            </div>
                        ))}
                    </div>
                </Card.Body>
            </Card>
        );
    }
}