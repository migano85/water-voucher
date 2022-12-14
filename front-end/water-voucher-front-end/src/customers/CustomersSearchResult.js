import React from "react";
import "./CustomersSearchResult.css";
function CustomersSearchResult({ customers }) {
  return (
    <div>
      <table>
        <tbody>
          <tr className="headerRow" key="0">
            <td width={300}>first name</td>
            <td width={300}>last name</td>
            <td width={300}>phone number</td>
          </tr>
          {customers.map((customer) => (
            <tr key={customer.customerId}>
              <td>{customer.firstName}</td>
              <td>{customer.lastName}</td>
              <td>{customer.phoneNo}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CustomersSearchResult;
