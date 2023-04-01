import React from "react";
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
            <tr key={book.bookId}>
              <td>{book.numberOfPages}</td>
              <td>{book.customer.firstName + " " + book.customer.lastName}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default BooksSearchResult;
