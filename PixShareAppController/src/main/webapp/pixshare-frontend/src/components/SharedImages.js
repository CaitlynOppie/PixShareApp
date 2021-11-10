import React from 'react';

import {Navbar, Container, Col, Card, Row} from 'react-bootstrap'

export default class SharedImages extends React.Component{
    render(){
        return(
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>My Images</Card.Header>
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