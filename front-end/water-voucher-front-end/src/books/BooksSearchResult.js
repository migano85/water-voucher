import React from "react";
function BooksSearchResult({ books }) {
  return (
    <div>
      <table>
        <tbody>
          <tr className="headerRow" key="0">
            <td width={300}>number of pages</td>
            <td width={300}>customer name</td>
            <td>Edit</td>
            <td>Delete</td>
          </tr>
          {books.map((book) => (
            <tr key={book.bookId}>
              <td>{book.numberOfPages}</td>
              <td>{book.customer.firstName + " " + book.customer.lastName}</td>
              <td>
                <Link to={`/customers/${customer.customerId}`}>
                  <img src={editImg} />
                </Link>
              </td>
              <td>
                <img
                  src={trashImg}
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

export default BooksSearchResult;
