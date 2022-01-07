import React, { Component } from 'react'
import StripeButton from './stripebutton'

class PaymentMethod extends Component {

    constructor(props) {
        super(props)
    
        this.state = {
             total: " ",

        }
        this.flight= this.props.location.state.flight
        this.ticket=this.props.location.state.ticket
        //this.fromCheckIn=this.props.location.state.fromCheckIn
        //this.preference=this.props.location.state?.pref
    }

    submitHandler=(e) => {
        e.preventDefault()
        this.props.history.push({
            pathname: `/paytmpg`,
            //state: this.state.total
            state:{total: this.state.total , ticket: this.ticket}
        })
    }

    

    render() {
        return (
            <div className="container">
                <br/>
                <h3 className="text-center" style={{color:'white'}}>Payment Details</h3>
                <div className= "text-payment">
                <h4 className="text-details" style={{color:'white'}}>Confirm Details</h4>
                <p className="label-payment" style={{color:'white'}}>First Name: {this.flight.first_name}</p>
                <p className="label-payment" style={{color:'white'}}>Last Name: {this.flight.last_name}</p>
                <p className="label-payment" style={{color:'white'}}>Flight Name: {this.ticket.flight_name}</p>
                <p className="label-payment" style={{color:'white'}}>Number of seats: {this.flight.quantity}</p>
                <p className="label-group">Total: {(parseInt(this.ticket.fare)*parseInt(this.flight.quantity))+parseInt(this.flight.seat_type)}</p>
                <StripeButton className="label-group" price= {(parseInt(this.ticket.fare)*parseInt(this.flight.quantity))+parseInt(this.flight.seat_type)} newFlight={this.flight}/>
                {/*{!this.fromCheckIn ? (<div><p className="label-group">Total: {(parseInt(this.ticket.fare)*parseInt(this.flight.quantity))+parseInt(this.flight.seat_type)}</p>
                <StripeButton className="label-group" price= {(parseInt(this.ticket.fare)*parseInt(this.flight.quantity))+parseInt(this.flight.seat_type)} newFlight={this.flight}/></div>):
            (<div><p className="label-group">Total: {this.preference}</p><StripeButton className="label-group" price= {parseInt(this.preference)} newFlight={this.flight}/></div>)}*/}
                </div>
            </div>
        )
    }
}

export default PaymentMethod
