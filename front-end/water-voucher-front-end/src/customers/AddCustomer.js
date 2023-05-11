import React from "react";
import { useState } from "react";
import { Link, useHistory } from "react-router-dom";

function AddCustomer(props) {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [phone, setPhone] = useState(0);
  // const [emailAddress, setEmailAddress] = useState("");
  // useEffect(() => {
  //     setEmailAddress(firstName + "." + lastName + "@learningcontainer.com");
  // }, [firstName, lastName]);

  const history = useHistory();
  const handleSubmit = (e) => {
    e.preventDefault();
    const customer = {
      firstName,
      lastName,
      phone,
    };
    fetch("http://localhost:8080/customers/customer", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(customer),
    }).then((res) => {
      if (!res.ok) {
        alert("something went wrong");
      } else {
        document.querySelector("button").disabled = true;
        history.push("/");
      }
    });
  };

  /*const handleClicked = (e) => {
        if (document.querySelector("#txtFirstName").value == "") {
            alert("write something");
        } else {
            document.querySelector("button").disabled = true;
        }
    };*/
  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>First name:</label>
        <input
          id="txtFirstName"
          type="text"
          required
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />
        <label>Last name:</label>
        <input
          type="text"
          required
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />
        <button>Add Customer</button>
        <Link className="linkAsButton" to="/">
          Customers
        </Link>
      </form>
    </div>
  );
}

export default AddCustomer;
