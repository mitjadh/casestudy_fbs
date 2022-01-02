import React, { Component } from 'react'
import axios from 'axios'
import history from "react-router";
import { Form } from 'formik';

class Searching extends Component {

  constructor(props) {
    super(props)
  
    this.state = {
      from_city: "Mumbai",
      to_city: "Mumbai",
      date_depart: "",
      tickets: []
    }
  }

  changeHandler1 = (e) =>{
    this.setState({
        from_city: e.target.value,
    });
}

changeHandler2 = (e) =>{
    this.setState({
        to_city: e.target.value
    });
}

dateHandler = (e) =>{
    this.setState({
        date_depart: e.target.value
    });
}


submitHandler=(e) => {
  e.preventDefault()
  console.log("Shweta")
    axios.get("http://localhost:8888/searchflights/flights/"+this.state.from_city+"/"+this.state.to_city+"/"+this.state.date_depart)
    .then(response => 
      {console.log(response.data)
      this.setState({tickets:response.data
      });
      this.props.history.push({
        pathname: `/availableflights`,
        state: {tickets: response.data}
      })
    })
    .catch(err=> console.log(err))
  }

  
  render() {
    return (
      <div className="container">
        <br/>
        <div  className= "searching-container">
        <form onSubmit={this.submitHandler} action="/">
          {/*<label className="label-group"><b>Starting City: </b><input type="text" required pattern="[a-zA-Z]+" oninvalid="setCustomValidity('Please enter on alphabets only. ')" name="from_city" value={this.state.from_city} onChange={this.changeHandler1}/></label>
          <label className="label-group"><b>Destination City: </b><input type="text" required pattern="[a-zA-Z]+" oninvalid="setCustomValidity('Please enter on alphabets only. ')" name="to_city" value={this.state.to_city} onChange={this.changeHandler2}/></label>*/}
           <label className="label-group">Starting City:</label>
           <select style={{color: "black"}} name="from_city" required id="from_city" value={this.state.from_city} onChange={this.changeHandler1}>
           <option style={{color: "black"}} value="Mumbai">Mumbai</option>
           <option style={{color: "black"}} value="Bangalore">Bangalore</option>
           <option style={{color: "black"}} value="Delhi">Delhi</option>
           <option style={{color: "black"}} value="Odisha">Odisha</option>
           <option style={{color: "black"}} value="Kolkata">Kolkata</option>
           <option style={{color: "black"}} value="Lucknow">Lucknow</option>
           </select><br/><br/>
           <label className="label-group">Destination City:</label>
           <select style={{color: "black"}} name="to_city" required id="to_city" value={this.state.to_city} onChange={this.changeHandler2}>
           <option style={{color: "black"}} value="Mumbai">Mumbai</option>
           <option style={{color: "black"}} value="Bangalore">Bangalore</option>
           <option style={{color: "black"}} value="Delhi">Delhi</option>
           <option style={{color: "black"}} value="Odisha">Odisha</option>
           <option style={{color: "black"}} value="Kolkata">Kolkata</option>
           <option style={{color: "black"}} value="Lucknow">Lucknow</option>
           </select><br/><br/>
          {/*<label className="label-group"><b>Date: </b><input type="text" required pattern="^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[13-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$" name="date_depart"  oninvalid="setCustomValidity('Please enter on numbers only. ')"placeholder="dd-mm-yyyy" value={this.state.date_depart} onChange={this.dateHandler}/></label><br/><br/>*/}
          <label className="label-group">Date:  <input type="date" placeholder="yyyy-mm-dd" required value={this.state.date_depart} onChange={this.dateHandler}/></label><br/><br/>
          <button className="btn btn-primary" type="submit">Submit</button>
        </form>
      </div>
      </div>
    )
  }
}

export default Searching
