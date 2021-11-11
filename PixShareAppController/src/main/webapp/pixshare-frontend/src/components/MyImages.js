import React from 'react';
import axios from 'axios';
import AddImage from "./AddImage";


import {Card, Container, Row, Col, Button} from 'react-bootstrap'
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
            .get("http://localhost:8090/pix-share/mvc/image/view/1/Caitlyn.png")
            .then(res => res.data)
                .then((data) => {
                this.setState({images: data});
        });
        console.log(this.state.images);
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
                        {/*{this.state.images.map((image) => (*/}
                            <div>
                                {/*    <img*/}
                                {/*        className="border border-success mt-3 w-50 d-inline"*/}
                                {/*        src={`http://localhost:8090/pix-share/mvc/image/view/1/Caitlyn.png`}*/}
                                {/*        key={image}*/}
                                {/*        alt="image"*/}
                                {/*    />*/}
                                {/*))}*/}
                                <img
                                    src={"http://localhost:8090/pix-share/mvc/image/view/1/Caitlyn.png"}

                                />
                                {' '}
                                <br/>
                                <div className="metadata">Image ID:</div>
                                <div className="metadata">Size:</div>
                                <div className="metadata">Date:</div>
                                <br/>
                                <Button
                                    variant="outline-light"
                                    type="button">
                                    <i className="fa fa-share-alt"></i>
                                </Button>
                                {' '}
                                <Button
                                    variant="outline-success"
                                    type="button">
                                    <i className="fa fa-download"></i>
                                </Button>
                                {' '}
                                <Button
                                    variant="outline-danger"
                                    type="button">
                                    <i className="fa fa-trash"></i>
                                </Button>
                            </div>
                    </div>
                </Card.Body>
            </Card>
        );
    }
}
