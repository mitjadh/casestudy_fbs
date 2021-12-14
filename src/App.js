import React, { Component } from "react";
import './App.css';
import AuthService from "./service/auth-service";

import Register from "./components/register.component";

import Login from "./components/login.component";
import Home from "./components/home.component";
import Profile from "./components/profile.component";
import BoardUser from "./components/board-user.component";
import { BrowserRouter as Router, Route, Switch, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import EventBus from "./comman/EventBus";

import Searching from './components/Searching';
import AvailableFlights from "./components/AvailableFlights"
import Booking from "./components/Booking"
import {Nav, Navbar, NavDropdown, MenuItem,  Tabs, ButtonToolbar, Button, Table, ButtonGroup, Row, Col, Grid, Panel, FormGroup, FormControl} from 'react-bootstrap';
import { Container } from 'reactstrap';
import CheckIn from "./components/CheckIn"
import PaymentMethod from "./components/PaymentMethod";
import Thankyou from "./components/Thankyou";
import createflg from "./components/createflg";
import deleteflg from "./components/deleteflg";
import updateflg from "./components/updateflg";


class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      //showModeratorBoard: false,
      showAdminBoard: false,
      showUserBoard: false,
      currentUser: undefined,
    };
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
        //showModeratorBoard: user.roles.includes("ROLE_MODERATOR"),
        showAdminBoard: user.roles.includes("ROLE_ADMIN"),
        showUserBoard: user.roles.includes("ROLE_USER")
      });
    }

  EventBus.on("logout", () => {
    this.logOut();
  });
}
  componentWillUnmount() {
    EventBus.remove("logout");
  }

  logOut() {
    AuthService.logout();
    this.setState({
      showAdminBoard: false,
      showUserBoard: false,
      currentUser: undefined,
    });
  }

  render(){
    const { currentUser, showUserBoard, showAdminBoard } = this.state;
    

    return (
      <div>

        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/"} className="navbar-brand">
            Flight Reservation System
          </Link>
          <div className="navbar-nav mr-auto">
          <li className="nav-item">
              <Link to={"/home"} className="nav-link">
                Home
              </Link>
            </li>

            {/*{showModeratorBoard && (
              <li className="nav-item">
                <Link to={"/mod"} className="nav-link">
                  Moderator Board
                </Link>
              </li>
            )}*/}

            {showAdminBoard && (
              <li className="nav-item">
                <Link to={"/admincreate"} className="nav-link">
                  CreateFlights
                </Link>
              </li>
            )}

            {showAdminBoard && (
              <li className="nav-item">
                <Link to={"/admindelete"} className="nav-link">
                  FlightList
                </Link>
              </li>
            )}


            {showUserBoard && (
             <li className="nav-item">
                <Link to={"/search"} className="nav-link">
                  SearchFlights
                </Link>
              </li>
            )}
           {showUserBoard && (
              <li className="nav-item">
                <Link to={"/checkin"} className="nav-link">
                  CheckIn
                </Link>
              </li>
           )}

            {/*{currentUser && (
              <li className="nav-item">
                <Link to={"/checkin"} className="nav-link">
                  CheckIn
                </Link>
              </li>
            )}*/}
          </div>

          {currentUser ? (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/profile"} className="nav-link">
                  {currentUser.username}
                </Link>
              </li>
              <li className="nav-item">
                <a href="/login" className="nav-link" onClick={this.logOut}>
                  LogOut
                </a>
              </li>
            </div>
          ) : (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/login"} className="nav-link">
                  Login
                </Link>
              </li>

              <li className="nav-item">
                <Link to={"/register"} className="nav-link">
                  Sign Up
                </Link>
              </li>
            </div>
          )}
        </nav>

        <Switch>
        <Route exact path={["/", "/home"]} component={Home} />
        <Route exact path="/login" component={Login} />
        <Route exact path="/register" component={Register} />
        <Route exact path="/profile" component={Profile} />
        <Route path="/user" component={BoardUser} />
        <Route path="/search" component={Searching}/>
        <Route path="/availableflights" component={AvailableFlights} />
        <Route path="/booking" component={Booking} />
        <Route path="/admincreate" component={createflg}/>
        <Route path="/admindelete" component={deleteflg}/>
        <Route path="/update" component={updateflg}/>
        <Route path="/checkin" component={CheckIn}/>
        <Route path="/payment" component={PaymentMethod}/>
        <Route path="/thankyou" component={Thankyou} />
        {/*<Route path="/paytmpg" component={StripeButton}/>
        <Route path="/checkinflightdetails" component={CheckInFlights} />*/}
        </Switch>
      </div>

    )
  }
    /*<Router>
    <div className="navImage" style={{alignItems:'center'}}>
        <h1>Flight Reservation</h1>
      </div>
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav mr-auto">
            <li className="navbar-item">
              <Link to="/" className="nav-link" style={{ color: "white" }}>
                Home
              </Link>
            </li>
          </ul>
        </div>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav mr-auto">
            <li className="navbar-item">
              <Link
                to="/flightlist"
                className="nav-link"
                style={{ color: "white" }}
              >
                Flight Availability
              </Link>
            </li>
          </ul>
        </div>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav mr-auto">
            <li className="navbar-item">
              <Link
                to="/signUp"
                className="nav-link"
                style={{ color: "white" }}
              >
                Register
              </Link>
            </li>
          </ul>
        </div>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav mr-auto">
            <li className="navbar-item">
              <Link
                to="/booking"
                className="nav-link"
                style={{ color: "white" }}
              >
                Book Flight Tickets
              </Link>
            </li>
          </ul>
        </div>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav mr-auto">
            <li className="navbar-item">
              <Link
                to="/payment"
                className="nav-link"
                style={{ color: "white" }}
              >
                Payment
              </Link>
            </li>
          </ul>
        </div>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav mr-auto">
            <li className="navbar-item">
              <Link
                to="/adminSignIn"
                className="nav-link"
                style={{ color: "white" }}
              >
                Admin
              </Link>
            </li>
          </ul>
        </div>
      </nav>

    <Switch>
        <Route path="/signUp" exact component={SignUp} />
        <Route path="/login" exact component={Login} />
    </Switch>

    </Router>*/}

export default App;
