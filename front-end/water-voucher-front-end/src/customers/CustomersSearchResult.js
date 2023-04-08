import React from "react";
import { Link, NavLink } from "react-router-dom";
import "./CustomersSearchResult.css";
import editImg from "../images/icons/edit-row-48.png";
import trashImg from "../images/icons/trash-48.png";
function CustomersSearchResult({ customers }) {
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
                  <img src={editImg} />
                </Link>
              </td>
              <td>
                <NavLink to="/booksSearch">
                  <img src={trashImg} />
                </NavLink>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CustomersSearchResult;
