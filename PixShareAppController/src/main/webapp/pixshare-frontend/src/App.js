import React from 'react';
import './App.css';
import NavigationBar from "./components/NavigationBar";
import Footer from "./components/Footer";
import MyImages from "./components/MyImages";
import SharedImages from "./components/SharedImages";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import AddImage from "./components/AddImage";

import {Col, Container, Row} from "react-bootstrap";

function App() {
    const marginTop = {
        marginTop:"1.5%"
    }
  return (
    <Router>
        <NavigationBar/>
        <Container>
            <Row>
                <Col lg={12} style={marginTop}>
                    <Switch>
                        <Route path="/" exact component={MyImages}/>
                        <Route path="/SharedImages" exact component={SharedImages}/>
                        <Route path="/AddImage" exact component={AddImage}/>
                    </Switch>
                </Col>
            </Row>
        </Container>
        <Footer/>
    </Router>
  );
}

export default App;
