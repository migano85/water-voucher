import { useEffect, useState } from "react";
import Select from "react-select";
import BooksSearchResult from "./BooksSearchResult";
import axios from "axios";

const BookSearch = () => {
  const [selectedOption, setSelectedOption] = useState(null);
  const [customerId, setCustomerIdValue] = useState();
  const [books, setBooks] = useState();
  const [customers, setCustomers] = useState();

  axios.get("http://localhost:8080/customers/all").then((res) => {
    setCustomers(res.data);
  });

  function handleSubmit() {
    e.preventDefault();

    axios
      .get("http://localhost:8080/books/customerId=" + customerId)
      .then((res) => {
        setBooks(res.data);
      });
  }

  function handleSelect(selectedValue) {
    setSelectedOption(selectedValue);
  }
  useEffect(() => {
    setCustomerIdValue(selectedOption.customerId);
  }, [selectedOption]);
  return (
    <div>
      <h2 className="title">Books</h2>
      <form onSubmit={handleSubmit}>
        <table>
          <tbody>
            <tr>
              <td>
                <label>Customer :</label>
              </td>
              <td>
                <Select
                  options={customers}
                  getOptionLabel={(e) => e.firstName + " " + e.lastName}
                  getOptionValue={(e) => e.customerId}
                  value={selectedOption}
                  onChange={handleSelect}
                />
              </td>
            </tr>
            <tr>
              <td colSpan="2">
                <button>search</button>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <Link className="linkAsButton" to="/addCustomer">
        ADD
      </Link>
      <Link className="linkAsButton" to="/addBook">
        ADD BOOK
      </Link>
      {error && <div>{error}</div>}
      {isPending && <div>Loading...</div>}
      {books && <BooksSearchResult books={books} />}
    </div>
  );
};

export default BookSearch;
