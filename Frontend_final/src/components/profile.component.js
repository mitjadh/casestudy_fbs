import React, { Component } from "react";
import AuthService from "../service/auth-service";

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      currentUser: AuthService.getCurrentUser()
    };
  }

  render() {
    const { currentUser } = this.state;

    return (
      <div className="container">
        <header className="jumbotron">
          <h3 style={{color: "white"}}>
            <strong style={{color: "white"}}>{currentUser.username}</strong> Profile
          </h3>
        </header>
        {/*<p>
          <strong>Token:</strong>{" "}
          {currentUser.accessToken.substring(0, 20)} ...{" "}
          {currentUser.accessToken.substr(currentUser.accessToken.length - 20)}
        </p>
        <p>
          <strong>Id:</strong>{" "}
          {currentUser.id}
        </p>*/}
        <p style={{color: "white"}}>
          <strong style={{color: "white"}}>Email:</strong>{" "}
          {currentUser.email}
        </p>
        <strong style={{color: "white"}}>Authorities:</strong>
        <ul style={{color: "white"}}>
          {currentUser.roles &&
            currentUser.roles.map((role, index) => <li key={index}>{role}</li>)}
        </ul>
      </div>
    );
  }
}