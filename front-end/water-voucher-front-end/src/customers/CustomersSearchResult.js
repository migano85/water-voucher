import React from "react";
import { Link } from "react-router-dom";
import "./CustomersSearchResult.css";
import editImg from "../images/icons/edit-row-48.png";
import trashImg from "../images/icons/trash-48.png";
import axios from "axios";
function CustomersSearchResult({ customers }) {
  // const handleDelete = (id) => {
  //   fetch("http://localhost:8080/customers/" + id, { method: "DELETE" })
  //     .then((res) => {
  //       console.log(res);
  //     })
  //     .catch((error) => {
  //       alert(error.message);
  //       console.error("There was an error!", error);
  //     });
  // };
  const handleDelete = (id) => {
    alert(id);
    // DELETE request using axios with error handling
    axios
      .delete("http://localhost:8080/customers/" + id)
      .then((response) => {
        alert("Delete successful");
        console.log(response);
      })
      .catch((error) => {
        alert(error.message);
        console.error("There was an error!", error);
      });
  };
  return (
    <div>
      <table>
        <tbody>
          <tr className="headerRow" key="0">
            <td width={300}>first name</td>
            <td width={300}>last name</td>
            <td width={300}>phone number</td>
            <td>Edit</td>
            <td>Delete</td>
          </tr>
          {customers.map((customer) => (
            <tr key={customer.customerId}>
              <td>{customer.firstName}</td>
              <td>{customer.lastName}</td>
              <td>{customer.phoneNo}</td>
              <td>
                <Link to={`/customers/${customer.customerId}`}>
                  <img src={editImg} alt="edit" />
                </Link>
              </td>
              <td>
                <img
                  src={trashImg}
                  alt="delete"
                  onClick={(e) => handleDelete(customer.customerId)}
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CustomersSearchResult;
