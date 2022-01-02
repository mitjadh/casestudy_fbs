import React, { Component } from 'react'
import axios from 'axios'
import { confirmAlert } from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css'; // Import css

class deleteflg extends Component {
    constructor(props) {
        super(props)
    
        this.state = {
            posts: [] ,
            flg: {}
        }
    }

        componentDidMount() {  
            axios.get(`http://localhost:8064/admin/getflg`)  
              .then(res => {  
                const posts = res.data;  
                this.setState({ posts });  
              })  
          } 

        deleteHandler(flight_id, e){  
            e.preventDefault()
            /*window.confirm("Do you want to delete train ?")
            axios.delete(`http://localhost:8064/admin/delflg/${flight_id}`)  
              .then(res => {  
                console.log(res);  
                console.log(res.data);  
            
                const posts = this.state.posts.filter(item => item.flight_id !== flight_id);  
                this.setState({ posts });  
              })*/
              confirmAlert({
                title: 'Confirm to submit',
                message: 'Do you want to update flight details? ',
                buttons: [
                  {
                    label: 'Yes',
                    onClick: axios.delete(`http://localhost:8064/admin/delflg/${flight_id}`)  
                    .then(res => {  
                      console.log(res);  
                      console.log(res.data);  
                  
                      const posts = this.state.posts.filter(item => item.flight_id !== flight_id);  
                      this.setState({ posts });  
                    })
                  },
                  {
                    label: 'No',
                    //onClick: () => alert('Click No')
                  }
                ]
              });
            }


        updateHandler(post,e){
          e.preventDefault()
          this.props.history.push({
            pathname: `/update`,
            state: {post: post}
          });
          console.log(post)
        }
    
    render() {
        return (
            <div className="container">
                
            <br/>
            <div className="table-container"><h3><b>Flights</b></h3></div>
            <table className="table table-dark table-striped " style={{ marginTop: 20}}>
              <thead>
                <tr>
              <td>Flight id</td>
              <td>Flight name</td>
              <td>Departure Date (yyyy-mm-dd)</td>
              <td>Starting City</td>
              <td>Destination City</td>
              <td>Departure Time</td>
              <td>Arrival Time</td>
              <td>Available Seats</td>
              <td>Fare</td>
              <td></td>
              <td></td>
              </tr>
              </thead>

              <tbody>
                {
                    this.state.posts.map((post) => (
                        <tr key = {post.flight_id}>
                                <td>{post.flight_id}</td>
                                <td>{post.flight_name}</td>
                                <td>{post.date_depart}</td>
                                <td>{post.from_city}</td>
                                <td>{post.to_city}</td>
                                <td>{post.time_depart}</td>
                                <td>{post.time_arrival}</td>
                                <td>{post.seats}</td>
                                <td>{post.fare}</td>

                                <td>  
                                <button className="btn btn-danger" onClick={(e) => this.deleteHandler(post.flight_id, e)}>Delete</button>  
                                </td>  
                                <td>
                                <button className="btn btn-primary" onClick={(e) => this.updateHandler(post,e)}>Update</button>
                                </td>
                                </tr>
                    ))}
              </tbody>
              </table>
              </div>
        )
    }
}

export default deleteflg
