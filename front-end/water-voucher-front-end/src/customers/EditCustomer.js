import axios from "axios";
import React from "react";
import { useEffect, useState } from "react";
import { Link, useHistory, useParams } from "react-router-dom";

function EditCustomer(props) {
  const { customerId } = useParams();
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [phone, setPhone] = useState(0);

  useEffect(() => {
    axios.get("http://localhost:8080/customers/" + customerId).then((res) => {
      // setCustomer(res.data);
      setFirstName(res.data.firstName);
      setLastName(res.data.lastName);
    });
  }, [customerId]);

  const history = useHistory();
  const handleSubmit = (e) => {
    e.preventDefault();
    const customer = {
      customerId,
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
        <button>Update Customer</button>
        <Link className="linkAsButton" to="/">
          Customers
        </Link>
      </form>
    </div>
  );
}

export default EditCustomer;
