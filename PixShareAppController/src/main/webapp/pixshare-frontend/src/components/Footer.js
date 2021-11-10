import React from 'react';

import {Navbar, Container, Col} from 'react-bootstrap'

export default class Footer extends React.Component{
    render(){
        let year = new Date().getFullYear();
        return(
            <Navbar fixed="bottom" bg="dark" variant="dark">
                <Container>
                    <Col lg={12} className="text-center text-muted">
                        <div>
                            {year}-{year +1}, All Rights Reserved by PixShare
                        </div>
                    </Col>
                </Container>
            </Navbar>
        );
    }
}