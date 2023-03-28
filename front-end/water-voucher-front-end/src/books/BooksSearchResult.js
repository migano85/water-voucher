import React from "react";
import "./CustomersSearchResult.css";
function BooksSearchResult({ books }) {
  return (
    <div>
      <table>
        <tbody>
          <tr className="headerRow" key="0">
            <td width={300}>number of pages</td>
            <td width={300}>customer name</td>
          </tr>
          {books.map((book) => (
            <tr key={book.numberOfPages}>
              <td>{customer.firstName}</td>
              <td>{customer.lastName + " " + customer.lastName}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default BooksSearchResult;
