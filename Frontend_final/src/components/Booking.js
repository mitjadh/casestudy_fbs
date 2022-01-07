import React, { Component } from 'react'
import axios from 'axios';
import {Link,Redirect} from "react-router-dom";

class Booking extends Component {
    constructor(props) {
        super(props)
    
        this.state = {
            first_name: " ",
            last_name: " ",
            seat_type: "500",
            seat_no: " ",
            quantity: " ",
            isFlightCreated: false

        }
        this.ticket=this.props.location.state.ticket
    }
    
    fnameHandler=(e) =>{
        const first_name = document.getElementById("first_name").value
        this.setState({
            first_name: e.target.value
        })
        console.log("First name: "+first_name)
    }

    lnameHandler=(e) => {
        const last_name = document.getElementById("last_name").value
        this.setState({
            last_name: e.target.value
        })
        console.log("Last name: "+last_name)
    }

    seatHandler = (e) => {
        const seat_type = document.getElementById("seat_type").value;
        this.setState({
            seat_type:e.target.value
        });
        console.log("Seat type: "+seat_type)
    }

    quantityHandler = (e) => {
        {/*const quantity = document.getElementById("quantity").value;*/}
        
        let seat_names="";
    [...Array(parseInt(e.target.value))].map((e, i) => {seat_names+=this.ticket.seat_start[0] + (parseInt(this.ticket.seat_start.substr(1)) + i)+", "})
        //console.log("Seat no: "+quantity)
        this.setState({
            quantity:e.target.value,
            seat_no: seat_names
        });
    }

    seatnoHandler = (e) => {
        const seat_no = document.getElementById("seat_no").value;
        this.setState({
            seat_no:e.target.value
        });
        console.log("Seat no: "+seat_no)
    }

    submitHandler= (event) => {
        event.preventDefault();

        const newFlight={
            first_name: this.state.first_name,
            last_name: this.state.last_name,
            seat_type: this.state.seat_type,
            seat_no: this.state.seat_no,
            quantity: this.state.quantity,
            flight_id:this.props.location.state.ticket.flight_id
        };

        console.log(newFlight)

        /*axios.post("http://localhost:8061/flightbooking/booking",newFlight)
        .then(response => response)
        .catch(error => console.log(error))*/
    this.props.history.push({
        pathname:`/payment`,
        //state: {flight:newFlight,ticket:this.ticket,fromCheckIn:false}
        state: {flight:newFlight,ticket:this.ticket}
    });
  };

  /*moredetailsHandler = (e) => {
      e.preventDefault()
      this.props.history.push({
          pathname: `/checkin`,
          state: {flight:newFlight,ticket:this.ticket}
      })
  }*/

  render() {
    if (this.state.isFlightCreated) {
      return <Redirect to="/availableflights" />;
    }
  }

    render() {
        return (
            <div className="container">
                <br/>
                <div  className= "booking-container">
                <h1 className="text-center" style={{color:'white'}}>{this.props.location.state.ticket.flight_name}</h1>
                <form>
                <label className="label-group"> First Name: <input type="text" required pattern="[a-zA-Z]+" oninvalid="setCustomValidity('Please enter on alphabets only. ')" name="first_name" id="first_name" value={this.state.first_name} onChange={this.fnameHandler}></input></label><br/><br/>
                <label className="label-group"> Last Name:  <input type="text" required pattern="[a-zA-Z]+" oninvalid="setCustomValidity('Please enter on alphabets only. ')"name="last_name" id="last_name" value={this.state.last_name} onChange={this.lnameHandler}></input></label><br/><br/>
                <label className="label-group">Seat type</label>
                <select style={{color: "black"}} name="seat_type" required id="seat_type" value={this.state.seat_type} onChange={this.seatHandler}>
                <option style={{color: "black"}} value="500">Economy</option>
                <option style={{color: "black"}} value="0">Regular</option>
                </select><br/><br/>
                <label className="label-group">Number of seats: <input type="number" required name="quantity" id="quantity" value={this.state.quantity} onChange={this.quantityHandler}/></label><br/><br/>
                {/*<label className="label-group">Seat No</label> */}
                {this.state.quantity>0 ? (<div>
                    {[...Array(parseInt(this.state.quantity))].map((e, i) => {return <p className="label-group" key={i}>{this.ticket.seat_start[0] + (parseInt(this.ticket.seat_start.substr(1)) + i)}</p>})}
                </div>):(<div></div>)}
                {/*<select style={{color: "black"}} name="seat_no" required id="seat_no" value={this.state.seat_no} onChange={this.seatnoHandler}>
                <option style={{color: "black"}} value="6C">6C</option>
                <option style={{color: "black"}} value="7B">7B</option>
                <option style={{color: "black"}} value="8A">8A</option>
                <option style={{color: "black"}} value="7A">7A</option>
                <option style={{color: "black"}} value="1E">1E</option>
                </select><br/><br/>*/}
                <button className="btn btn-primary" type="submit" onClick={this.submitHandler}>Pay Now</button><br/><br/>
                {/*<button className="btn btn-primary" onClick={this.moredetailsHandler}>More Details</button><br/>*/}
                </form>
                </div>
            </div>
        )
    }
}

export default Booking