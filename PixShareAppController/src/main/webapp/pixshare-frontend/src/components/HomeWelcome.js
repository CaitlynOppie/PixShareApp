import React from "react";


class HomeWelcome extends React.Component {
    render() {
        return (
            <div className ="jumbotron">
                <h1> Welcome PixSharer</h1>
                <blockquote className="blockquote mb-0">
                    <p>
                        PixShare is a platform to help you save and share your pictures. We hope you enjoy your journey with us.
                    </p>
                    <footer className="blockquote-footer">
                        PixShare Family
                    </footer>

                    <p className="sideNote">
                        Simply click on MyImages to navigate to all your uploaded pictures or click on SharedImages to see all the pictures shared with you by fellow PixSharers.
                    </p>
                </blockquote>
            </div>
        )
    }
}
export default HomeWelcome;