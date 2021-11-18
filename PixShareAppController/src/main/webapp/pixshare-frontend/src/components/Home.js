import React from "react";
import {Carousel} from "react-bootstrap";

export default class Home extends React.Component {

    render() {
        localStorage.setItem('loggedIn', 'Log In');
        return (
            <Carousel>
                <Carousel.Item interval={1000}>
                    <img
                        className="d-block w-100"
                        src="https://upload.wikimedia.org/wikipedia/commons/8/87/Red_sunset.jpg"
                        alt="sunset"
                        height="500px"
                    />
                    <Carousel.Caption>
                        <h3>Welcome to PixShare</h3>
                        <p>The place to save and share all your photos</p>
                    </Carousel.Caption>
                </Carousel.Item>
                <Carousel.Item interval={1000}>
                    <img
                        className="d-block w-100"
                        src="https://upload.wikimedia.org/wikipedia/commons/6/6e/T%C3%BCrlersee_panosphere_20200730.jpg"
                        alt="Second slide"
                        height="500px"
                    />
                </Carousel.Item>
                <Carousel.Item interval={1000}>
                    <img
                        className="d-block w-100"
                        src="https://upload.wikimedia.org/wikipedia/commons/3/3e/A_Female_Lion_%28137135031%29.jpeg"
                        alt="Third slide"
                        height="500px"
                    />
                </Carousel.Item>
            </Carousel>
        );
    }
}