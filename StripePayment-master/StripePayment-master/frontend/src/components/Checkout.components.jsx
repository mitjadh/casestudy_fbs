import React from "react";
import headphones from "./../images/headphones.jpg";
import applewatch from "./../images/applewatch.jpg";
import "./Checkout.styles.scss";
import StripeButton from "./stripebutton.component";

const Checkout = () => {
  return (
    <div className="checkout">
      <div className="header">
        <div className="header-block">
          <span>CHECKOUT</span>
        </div>
      </div>
      <div className="item-details-container">
        <div className="item-1">
          <div>
            <img src={headphones} alt="item" />
            <div className="desc">Senheisser MX320</div>
            <div className="qty">Quantity : 1</div>
          </div>
          <div className="price">Price : $299</div>
        </div>
        <div className="item-2">
          <div>
            <img src={applewatch} alt="item" />
            <div className="desc">Apple Watch Series 5</div>
            <div className="qty">Quantity : 1</div>
          </div>
          <div className="price">Price : $349</div>
        </div>
      </div>
      <div className="total">TOTAL : $648</div>
      <StripeButton price="648" />
    </div>
  );
};

export default Checkout;
