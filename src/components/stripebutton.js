import React from "react";
import StripeCheckout from "react-stripe-checkout";
import axios from "axios";
import { useHistory } from "react-router-dom";

const StripeButton = ({ price , newFlight,fromCheckIn,bookingid}) => {
  const publishableKey = "pk_test_51JfKgCSCA3K9LC6sGZjedefQw8FFRJ0WCQXAvGOtlnjNLIokRwTSVII6SSxDeRhR30tL5uzXZgTceORU4g10iaq400D6hD4IY2";
  const stripePrice = price * 100 ;
  const history = useHistory()
  let data={}
  
  const onToken = (token) => {
    console.log(token);
    console.log(newFlight)
    axios
      .post("http://localhost:8083/payment", {
        amount: stripePrice,
        token,
      })
      .then((response) => {
        console.log(response);
        alert("payment success");
        if(!fromCheckIn){
          axios.post("http://localhost:8061/flightbooking/booking",newFlight)
          .then(response => {console.log(response);history.push({
            pathname:`/thankyou`,
            state: {_id: response.data.id}})})
          .catch(error => console.log(error))}
          else{
          history.push({
            pathname:`/thankyou`,
            state: {_id: bookingid}})
          }
      })
      .catch((error) => {
        console.log(error);
        alert("Payment success");
      })
        /*if(!fromCheckIn){
        axios.post("http://localhost:8061/flightbooking/booking",newFlight)
        .then(response => {console.log(response);history.push({
          pathname:`/thankyou`,
          state: {_id: response.data.id}})})
        .catch(error => console.log(error))}
        else{
        history.push({
          pathname:`/thankyou`,
          state: {_id: bookingid}})
        }
      });*/

  };


  return (
    <StripeCheckout
      amount={stripePrice}
      label="Pay Now"
      image="https://svgshare.com/i/CUz.svg"
      description={`Your total is ${price}`}
      panelLabel="Pay Now"
      token={onToken}
      stripeKey={publishableKey}
      currency="INR"
    />
  );
};

export default StripeButton;

