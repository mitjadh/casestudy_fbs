import React, { Component } from 'react'
import {Link} from "react-router-dom"
import axios from 'axios';
import { Formik, Form, Field } from "formik";

    class AvailableFlights extends Component {
    constructor(props) {
        super(props)
    
        this.state = {

             tickets: this.props.location.state.tickets
        }
        //this.bookTicket=this.bookTicket.bind(this);

      }

    bookTicket = (ticket) => {
      this.props.history.push({pathname: `/booking`,
      state: {ticket: ticket}}); //passing ticket as state
      console.log(ticket)
    }

    render() {

        return (
            <div className="container">
                
                    <br/>
                    {this.state.tickets.length>0 ? (<div>
                    <div className="table-container"><h3><b>Flights Available</b></h3></div>
                    <table className="table table-dark table-striped " style={{ marginTop: 20}}>
                      <thead>
                        <tr>
                      <td>Flight id</td>
                      <td>Flight name</td>
                      <td>Flight departure</td>
                      <td>Flight starting</td>
                      <td>Flight Destination</td>
                      <td>Departure Time</td>
                      <td>Arrival Time</td>
                      <td>Seats</td>
                      <td>Fare</td>
                      <td>Booking</td>
                      </tr>
                      </thead>

                      <tbody>
                        {
                          this.state.tickets.map(
                            ticket =>
                              <tr key = {ticket.flight_id}>
                                <td>{ticket.flight_id}</td>
                                <td>{ticket.flight_name}</td>
                                <td>{ticket.date_depart}</td>
                                <td>{ticket.from_city}</td>
                                <td>{ticket.to_city}</td>
                                <td>{ticket.time_depart}</td>
                                <td>{ticket.time_arrival}</td>
                                <td>{ticket.seats}</td>
                                <td>{ticket.fare}</td>
                                {/*<td><Link to={{pathname:`/booking`,state: this.state.tickets}}>button</Link></td>*/}
                                <td><button className="btn btn-primary" onClick={()=> {this.bookTicket(ticket)}}>Book</button></td>
                                
                                </tr>
                                
                          )
                        }
                      </tbody>
                    </table>
                    </div>) : (<div><div style={{color: "white"}}><b> No flight found </b></div><br/>
                    <button className="btn btn-primary" onClick={() => {this.props.history.push(`/search`)}}>Search Again</button>
                    </div>)
    }

                    {/*<form>
                    <label>Starting City: <input type="text" name="from_city" value={this.state.from_city} onChange={this.changeHandler1}/></label>
                    <label>Destination City: <input type="text" name="to_city" value={this.state.to_city} onChange={this.changeHandler2}/></label>
                    <label>Date :<input type="text" name="date_depart" placeholder="dd-mm-yyyy" value={this.state.date_depart} onChange={this.dateHandler}/></label>
                    <button type="submit" onClick={this.submitHandler}>Submit</button>
                    </form>*/}
            </div>
            
        )
    }
}

export default AvailableFlights
