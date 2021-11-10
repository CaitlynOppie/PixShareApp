import React from 'react';
import AddImage from "./AddImage";

import {Card, Container, Row, Col, Button} from 'react-bootstrap'
import {Link} from "react-router-dom";

export default class MyImages extends React.Component{
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
                                    width="90%"
                                    height="90%"
                                />
                            </Col>
                        </Row>
                    </Container>
                </Card.Body>
            </Card>
        );
    }
}
