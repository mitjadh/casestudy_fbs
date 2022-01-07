import React, { Component } from 'react'
import axios from 'axios';
import {Link,Redirect} from "react-router-dom";
import { confirmAlert } from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css'; // Import css

class createflg extends Component {
    constructor(props) {
        super(props)
    
        this.state = {
            flight_id: "",
            flight_name: "JetAirways",
            date_depart: "",
            from_city: "Mumbai ",
            to_city: "Mumbai ",
            time_depart: "",
            time_arrival: "", 
            seats: "",
            fare: "",
            isFlightCreated: false
        }
    }
    idHandler=(e) =>{
        const flight_id = document.getElementById("flight_id").value
        this.setState({
            flight_id: e.target.value
        })
        console.log("Flight Id: "+flight_id)
    }

    nameHandler=(e) => {
        const flight_name = document.getElementById("flight_name").value
        this.setState({
            flight_name: e.target.value
        })
        console.log("Flight name: "+flight_name)
    }

    dateHandler=(e) => {
        const date_depart = document.getElementById("date_depart").value
        this.setState({
            date_depart: e.target.value
        })
        console.log("Date of Departure: "+date_depart)
    }

    fcityHandler=(e) => {
        const from_city = document.getElementById("from_city").value
        this.setState({
            from_city: e.target.value
        })
        console.log("Starting city: "+from_city)
    }
    
    tcityHandler=(e) => {
        const to_city = document.getElementById("to_city").value
        this.setState({
            to_city: e.target.value
        })
        console.log("Destination city: "+to_city)
    }

    fcityHandler=(e) => {
        const from_city = document.getElementById("from_city").value
        this.setState({
            from_city: e.target.value
        })
        console.log("Starting city: "+from_city)
    }

    dtimeHandler=(e) => {
        const time_depart = document.getElementById("time_depart").value
        this.setState({
            time_depart: e.target.value
        })
        console.log("Departure Time: "+time_depart)
    }

    atimeHandler=(e) => {
        const time_arrival = document.getElementById("time_arrival").value
        this.setState({
            time_arrival: e.target.value
        })
        console.log("Arrival Time: "+time_arrival)
    }
    
    seatsHandler=(e) => {
        const seats = document.getElementById("seats").value
        this.setState({
            seats: e.target.value
        })
        console.log("Departure Time: "+seats)
    }

    fareHandler=(e) => {
        const fare = document.getElementById("fare").value
        this.setState({
            fare: e.target.value
        })
        console.log("Departure Time: "+fare)
    }

    submitHandler = event => {
        event.preventDefault();
    
        const newFlg = {
          flight_id: this.state.flight_id,
          flight_name: this.state.flight_name,
          date_depart: this.state.date_depart,
          from_city: this.state.from_city,
          to_city: this.state.to_city,
          time_depart: this.state.time_depart,
          time_arrival: this.state.time_arrival,
          seats: this.state.seats,
          fare: this.state.fare
        };
        console.log(newFlg)

        /*axios.post("http://localhost:8064/admin/addflg",newFlg)
        .then(response => response)
        .catch(error => error.message);
        window.alert("Flight created successfully");
        */

        confirmAlert({
            title: 'Confirm to submit',
            message: 'Do you want to add flight details? ',
            buttons: [
              {
                label: 'Yes',
                onClick: () => axios.post("http://localhost:8064/admin/addflg",newFlg)
                .then(response => response)
                .catch(error => error.message)
              },
              {
                label: 'No',
                //onClick: () => alert('Click No')
              }
            ]
          });
        this.setState({
            flight_id: " ",
            flight_name: " ",
            date_depart: " ",
            from_city: " ",
            to_city: " ",
            time_depart: " ",
            time_arrival: " ",
            seats: " ",
            fare: " ",
            isFlightCreated: true
    });
    console.log(newFlg)
    };

    render() {
            if (this.state.isFlightCreated) {
              return <Redirect to="/admindelete" />;
            }

        return (
            <div className="container">
                <br/>
                <div  className= "admincreate-container">
                <br/><h4 className="text-center" style={{color:'white'}}>Create New Flight</h4>
                <form>
                <label className="label-group">Flight Id: <input type="number" required name="flight_id" id="flight_id" value={this.state._id} onChange={this.idHandler}></input></label><br/><br/>

                <label className="label-group"> Flight Name: </label>
                <select style={{color: "black"}} name="flight_name" required id="flight_name" value={this.state.flight_name} onChange={this.nameHandler}>
                <option style={{color: "black"}} value="JetAirways">JetAirways</option>
                <option style={{color: "black"}} value="SpiceJet">SpiceJet</option>
                <option style={{color: "black"}} value="Air India">Air India</option>
                <option style={{color: "black"}} value="GoAir">GoAir</option>
                <option style={{color: "black"}} value="Air Asia">Air Asia</option>
                <option style={{color: "black"}} value="Vistara">Vistara</option>
                </select><br/><br/>

                {/*<label className="label-group"><b>Date of Departure: </b><input type="text" required pattern="^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[13-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$" name="date_depart" id="date_depart"  oninvalid="setCustomValidity('Please enter on numbers only. ')"placeholder="dd-mm-yyyy" value={this.state.date_depart} onChange={this.dateHandler}/></label><br/><br/>*/}
                <label className="label-group">Date: <input type="date" required name= "date_depart" id="date_depart" placeholder="yyyy-mm-dd" value={this.state.date_depart} onChange={this.dateHandler}/></label><br/><br/>

                <label className="label-group">Starting City: </label>&nbsp;&nbsp;
                <select style={{color: "black"}} name="from_city" required id="from_city" value={this.state.from_city} onChange={this.fcityHandler}>
                <option style={{color: "black"}} value="Mumbai">Mumbai</option>
                <option style={{color: "black"}} value="Bangalore">Bangalore</option>
                <option style={{color: "black"}} value="Delhi">Delhi</option>
                <option style={{color: "black"}} value="Odisha">Odisha</option>
                <option style={{color: "black"}} value="Kolkata">Kolkata</option>
                <option style={{color: "black"}} value="Lucknow">Lucknow</option>
                </select><br/><br/>

                <label className="label-group">Destination City:</label>&nbsp;&nbsp;
                <select style={{color: "black"}} name="to_city" required id="to_city" value={this.state.to_city} onChange={this.tcityHandler}>
                <option style={{color: "black"}} value="Mumbai">Mumbai</option>
                <option style={{color: "black"}} value="Bangalore">Bangalore</option>
                <option style={{color: "black"}} value="Delhi">Delhi</option>
                <option style={{color: "black"}} value="Odisha">Odisha</option>
                <option style={{color: "black"}} value="Kolkata">Kolkata</option>
                <option style={{color: "black"}} value="Lucknow">Lucknow</option>
                </select><br/><br/>
                
                <label className="label-group">Departure Time: <input type="text" required name="time_depart" id="time_depart" oninvalid="setCustomValidity('Please enter in proper format. ')"placeholder="hh:mm pm/am" value={this.state.time_depart} onChange={this.dtimeHandler}></input></label>&nbsp;&nbsp;<br/><br/>

                <label className="label-group">Arrival Time: <input type="text" required name="time_arrival" id="time_arrival" oninvalid="setCustomValidity('Please enter in proper format. ')"placeholder="hh:mm pm/am" value={this.state.time_arrival} onChange={this.atimeHandler}></input></label>&nbsp;&nbsp;<br/><br/>

                <label className="label-group">Seats Available: <input type="number" required name="seats" id="seats" oninvalid="setCustomValidity('Please enter in number format only. ')" value={this.state.seats} onChange={this.seatsHandler}/></label>&nbsp;&nbsp;<br/><br/>

                <label className="label-group">Fare: <input type="number" required name="fare" id="fare" oninvalid="setCustomValidity('Please enter in number format only. ')" value={this.state.fare} onChange={this.fareHandler}/></label>&nbsp;&nbsp;<br/><br/>
                
                <button className="btn btn-primary" type="submit" onClick={this.submitHandler}>Create</button><br/><br/>
                {/*<button className="btn btn-primary" onClick={this.moredetailsHandler}>More Details</button><br/>*/}
                </form>
                </div>
            </div>
        )
    }
}

export default createflg