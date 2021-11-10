import React from 'react';
import axios from 'axios';
import AddImage from "./AddImage";


import {Card, Container, Row, Col, Button} from 'react-bootstrap'
import {Link} from "react-router-dom";

export default class MyImages extends React.Component{

    // constructor(props) {
    //     super(props);
    //     this.state ={
    //         Images : []
    //     };
    // }
    //
    // componentDidMount() {
    //     axios.get("https://localhost:8090/pixshare/mvc/image/viewAll/{userID}")
    //         .then(response => response.data);
    // }

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
                                src="https://upload.wikimedia.org/wikipedia/commons/a/ad/2016_Phnom_Penh%2C_Pa%C5%82ac_Kr%C3%B3lewski%2C_Preah_Tineang_Phhochani_%2814%29.jpg" rounded
                                width="70%"
                                height="70%"
                            />
                            {' '}
                            <br/>
                            <br/>
                            <Button
                                variant="outline-light"
                                type="button">
                                <i class="fa fa-share-alt"></i>
                            </Button>
                            {' '}
                            <Button
                                variant="outline-success"
                                type="button">
                                <i class="fa fa-download"></i>
                            </Button>
                            {' '}
                            <Button
                                variant="outline-danger"
                                type="button">
                                <i class="fa fa-trash"></i>
                            </Button>
                        </Col>

                    </Row>
                    </Container>
                </Card.Body>
            </Card>
        );
    }
}
