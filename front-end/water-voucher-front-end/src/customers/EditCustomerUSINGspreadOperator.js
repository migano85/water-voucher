import axios from "axios";
import React from "react";
import { useEffect, useState } from "react";
import { Link, useHistory, useParams } from "react-router-dom";

function EditCustomer(props) {
  const { customerId: id } = useParams();
  const [customer, setCustomer] = useState({});
  // console.log(id);
  useEffect(() => {
    axios.get("http://localhost:8080/customers/" + id).then((res) => {
      setCustomer(res.data);
    });
  }, []);
  const history = useHistory();
  const handleSubmit = (e) => {
    e.preventDefault();

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
          value={customer.firstName}
          onChange={(e) =>
            setCustomer({ ...customer, firstName: e.target.value })
          }
        />
        <label>Last name:</label>
        <input
          type="text"
          required
          value={customer.lastName || "null value here"}
          onChange={(e) =>
            setCustomer({ ...customer, lastName: e.target.value })
          }
        />
        <button>Add Customer</button>
        <Link className="linkAsButton" to="/">
          Customers
        </Link>
      </form>
    </div>
  );
}

export default EditCustomer;
