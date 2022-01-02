import axios from 'axios'
import React, { Component } from 'react'

export class Thankyou extends Component {
    constructor(props) {
        super(props)

        this.data=this.props.location.state._id
    }

    componentDidMount(){
        axios.post("http://localhost:8080/sms",{
            "to": "+917506104222",
            "message": "payment has been confirmed"    
        }
        )
    }
    redirectHandler = (e) => {
        e.preventDefault()
            this.props.history.push(`/search`)
    }
    
    render() {
        return (
            <div className="container">
                <br/>
                <div  className= "ty-container">
                <div className="text-center" style={{color:'white'}}>
                <h3>Thankyou!</h3><br/><br/>
                Your Booking has been confirmed <br/>
                Booking Id: {this.data}<br/>
                Payment Confirmation has been sent to your number<br/>
                You can view your details with CheckIn <br/>
                <button className="btn btn-primary" type="submit" onClick={this.redirectHandler}>HomePage</button><br/>
                </div>
                </div>
            </div>
        )
    }
}

export default Thankyou
