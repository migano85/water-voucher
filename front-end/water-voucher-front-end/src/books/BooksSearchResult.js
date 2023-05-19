import React from "react";
import { Link } from "react-router-dom";
import editImg from "../images/icons/edit-row-48.png";
import trashImg from "../images/icons/trash-48.png";
import axios from "axios";

function BooksSearchResult({ books }) {
  const handleDelete = (id) => {
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
            <td width={300}>number of pages</td>
            <td width={300}>customer name</td>
            <td>Edit</td>
            <td>Delete</td>
            <td>vouchers</td>
          </tr>
          {books.map((book) => (
            <tr key={book.bookId}>
              <td>{book.numberOfPages}</td>
              <td>{book.customer.firstName + " " + book.customer.lastName}</td>
              <td>
                <Link to={`/books/${book.bookId}`}>
                  <img src={editImg} alt="edit" />
                </Link>
              </td>
              <td>
                <img
                  src={trashImg}
                  alt="delete"
                  onClick={(e) => handleDelete(book.bookId)}
                />
              </td>
              <td>
                <Link to={`/vouchers?bookId=${book.bookId}`}>voucher</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default BooksSearchResult;
