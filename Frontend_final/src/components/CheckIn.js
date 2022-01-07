import axios from 'axios'
import React, { Component } from 'react'

class CheckIn extends Component {

    constructor(props) {
        super(props)
    
        this.state = {
             _id: " ",
             seat_type: " ",
             seat_no: " ",
             quantity: " ",
             first_name: " ",
             last_name: " ",
             flight_id: " ",
             checkin: {},
             flight_name: " ",
             date_depart: " ",
             from_city: " ",
             to_city: " ",
             time_depart: " ",
             time_arrival: " ",
             fare:" ",
             flight: {},
             //declare1: " ",
             //declare2: " ",
             //preference: "500"
        }
        //this.from_booking=this.props.location.state
    }

    idHandler= (event) => {
        this.setState({
            _id:event.target.value
        })
    }

    /*declare1Handler= (e) => {
        this.setState({
            declare1: e.target.value
        })
    }

    declare2Handler= (e) => {
        this.setState({
            declare2: e.target.value
        })
    }
    preferenceHandler= (e) => {
        this.setState({
            preference: e.target.value
        })
    }*/

    submitHandler= (e) => {
        e.preventDefault()
        //console.log(this.from_booking)
        axios.get("http://localhost:8888/checkin/"+this.state._id)
        .then(response => 
            {console.log(response.data)
            this.setState({checkin: response.data
            })
        })
        .catch(err => console.log(err))
    }

    deleteHandler= (e) => {
        e.preventDefault()
       window.alert("Do you want to cancel booking ?")
        axios.delete("http://localhost:8062/checkin/del/"+this.state._id)
        .then(response => 
            {console.log(response.data)
            this.setState({checkin: response.data
            })
        })
        .catch(err => console.log(err))

    }

    flightHandler= (e) => {
        e.preventDefault()
        axios.get("http://localhost:8888/searchflights/flights/"+this.state.checkin.flight_id)
        .then(response => 
            {console.log(response.data)
            this.setState({flight:response.data
            });
        })
        .catch(err => console.log(err))
    }
        /*this.props.history.push({pathname: `/checkinflightdetails`,
        state:{checkin: response.data}
    });*/
    
    payHandler= (e) => {
        e.preventDefault()
        console.log( this.state.checkin)
        console.log( this.state.flight)
        this.props.history.push({
            pathname: `/payment`,
            //state: {from_booking: this.from_booking}
            //state: {flight: this.state.checkin , ticket: this.state.flight, pref: this.state.preference, fromCheckIn:true,}
            state: {flight: this.state.checkin , ticket: this.state.flight}
        })
    }
    
    render() {
        return (
            
            <div className="container">
                <br/>
                <h3 className="text-center" style={{color:'white'}}>CheckIn Details</h3>
                <form>
                    <label className="label-group">Booking Id: <input type="number" required name="_id" id="_id" value={this.state._id} onChange={this.idHandler}></input></label>&nbsp;&nbsp;&nbsp;<br/>
                    <button className="btn btn-primary" onClick={this.submitHandler}>View Booking Details</button>&nbsp;&nbsp;&nbsp;
                    <button className="btn btn-danger btn-sm" onClick={this.deleteHandler}>Cancel Booking</button><br/>
                    {/*<input type="checkbox" name="declare1" style={{color: "white"}} required value={this.state.declare1} onChange={this.declare1Handler}/>
                     I declare that I am not carrying any explosives or harmful substances<br/>
                    <input type="checkbox" name="declare2" style={{color: "white"}}  required value={this.state.declare2} onChange={this.declare2Handler}/>
                I declare that I am not carrying any food items <br/>*/}
                    {/*<label className="checkin-group">Preferences: </label>
                    <select style={{color: "black"}} name="preference" id="preference" value={this.state.preference} onChange={this.preferenceHandler}>
                    <option style={{color: "black"}} value="500">Breakfast Box</option>
                    <option style={{color: "black"}} value="600">LunchBox</option>
                    <option style={{color: "black"}} value="600">DinnerBox</option>
                    <option style={{color: "black"}} value="70">Tea/Coffee</option>
                    </select><br/>
            <button className="btn btn-primary" type="submit" onClick={this.payHandler}>Pay for Add-ons</button>*/}
                    
                </form>
                {/*checkin changed from array[] to object{} as we are dealing with just one record*/}
                {/*Object.keys allows us to access the keys inside that object i.e flight_id etc and create table according to values stored*/}


                {Object.keys(this.state.checkin).length>0 ? 
                (<table className="table table-dark table-striped " style={{ marginTop: 20}}>
                      <thead>
                        <tr>
                      <td>Booking id</td>
                      <td>First Name</td>
                      <td>Last Name</td>
                      <td>Seat type</td>
                      <td>Seat No</td>
                      <td>Quantity</td>
                      <td>Flight Id</td>
                      <td>Flight Details</td>
                      </tr>
                      </thead>

                      <tbody>
                              <tr key = {this.state.checkin.flight_id}>
                                <td>{this.state.checkin._id}</td>
                                <td>{this.state.checkin.first_name}</td>
                                <td>{this.state.checkin.last_name}</td>
                                <td>{this.state.checkin.seat_type}</td>
                                <td>{this.state.checkin.seat_no}</td>
                                <td>{this.state.checkin.quantity}</td>
                                <td>{this.state.checkin.flight_id}</td>
                                <td><button className="btn btn-primary" onClick={this.flightHandler}>Flight Details</button></td>
                                
                                </tr>
                                
                          
                      </tbody>
                    </table>
        ): (<div></div>)}

                    {Object.keys(this.state.flight).length>0 ? 
                    (<table className="table table-dark table-striped " style={{ marginTop: 20}}>
                      <thead>
                        <tr>
                      <td>Flight id</td>
                      <td>Flight Name</td>
                      <td>Date of Departure</td>
                      <td>Starting City</td>
                      <td>Destination City</td>
                      <td>Departure Time</td>
                      <td>Arrival Time</td>
                      <td>Fare</td>
                      </tr>
                      </thead>

                      <tbody>
                              <tr key = {this.state.checkin.flight_id}>
                                <td>{this.state.checkin.flight_id}</td>
                                <td>{this.state.flight.flight_name}</td>
                                <td>{this.state.flight.date_depart}</td>
                                <td>{this.state.flight.from_city}</td>
                                <td>{this.state.flight.to_city}</td>
                                <td>{this.state.flight.time_depart}</td>
                                <td>{this.state.flight.time_arrival}</td>
                                <td>{this.state.flight.fare}</td>
                                </tr>
                      </tbody>
                    </table>):(<div></div>)}
                {/*<p>First Name: {this.state.checkin.first_name}</p>
                <p>Last Name: {this.state.checkin.last_name}</p>
                <p>Seat Type: {this.state.checkin.seat_type}</p>
                <p>Seat No: {this.state.checkin.seat_no}</p>
                <p>Number of seats: {this.state.checkin.quantity}</p>
                <p>Flight Id: {this.state.checkin.flight_id}<button className="btn btn-primary" onClick={this.flightHandler}>Flight Details</button></p>
                <p>Flight Name: {this.state.flight.flight_name}</p>
                <p>Date of Departure : {this.state.flight.date_depart}</p>
                <p>Starting City: {this.state.flight.from_city}</p>
                <p>Destination City: {this.state.flight.to_city}</p>
                <p>Departure Time: {this.state.flight.time_depart}</p>
        <p>Arrival Time: {this.state.flight.time_arrival}</p>*/}
                 </div>
                 
        )
    }
}

export default CheckIn