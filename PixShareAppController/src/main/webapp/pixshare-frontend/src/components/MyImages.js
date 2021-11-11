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
            .get("http://localhost:8090/pix-share/mvc/image/view/Caitlyn.png/6")
            .then(resp => {
                console.log(resp.data);
                // this.setState({images: resp.data});
            });
        // console.log(this.state.images);
    }

    render(){
        return(
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>
                    My Images
                    {' '}
                    <Link to={"AddImage"} className="btn btn-outline-light">Add Image</Link>
                </Card.Header>
                <Card.Body>
                    <Container>
                        <Row>
                        <Col xs={6} md={4}>
                            <img
                                src="http://localhost:8090/pix-share/mvc/image/view/Caitlyn.png/6"
                                width="70%"
                                height="70%"
                            />
                            {' '}
                            <br/>
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
                        </Col>

                    </Row>
                    </Container>
                    {/*<div>*/}
                    {/*    {this.state.images.map((img) => (*/}
                    {/*        <div className={"grouping"}>*/}
                    {/*            <img*/}
                    {/*                key={img + "6"}*/}
                    {/*                src="http://localhost:8090/pix-share/mvc/image/view/Caitlyn.png/6"*/}
                    {/*                width="70%"*/}
                    {/*                height="70%"*/}
                    {/*            />*/}
                    {/*        </div>*/}
                    {/*    ))}*/}
                    {/*</div>*/}
                </Card.Body>
            </Card>
        );
    }
}
