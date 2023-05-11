import { useEffect, useState } from "react";
import Select from "react-select";
import BooksSearchResult from "./BooksSearchResult";
import axios from "axios";
import { Link } from "react-router-dom";

const BookSearch = () => {
  const [selectedOption, setSelectedOption] = useState(null);
  const [customerId, setCustomerIdValue] = useState();
  const [books, setBooks] = useState();
  const [customers, setCustomers] = useState();

  //onComponentMount - first time
  useEffect(() => {
    axios.get("http://localhost:8080/customers/all").then((res) => {
      setCustomers(res.data);
    });
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    axios
      .get("http://localhost:8080/books/customerId=" + customerId)
      .then((res) => {
        setBooks(res.data);
      });
  };

  function handleSelect(selectedValue) {
    setSelectedOption(selectedValue);
    setCustomerIdValue(selectedValue.customerId);
    //******** ask lakchmi why the following gives customerId undefined
    // if (selectedOption != null) {
    //   setCustomerIdValue(selectedOption.customerId);
    //   console.log(customerId, "custId");
    // }
  }

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
                <button>SEARCH</button>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      <Link className="linkAsButton" to="/addBook">
        ADD BOOK
      </Link>
      {books && <BooksSearchResult books={books} />}
    </div>
  );
};

export default BookSearch;
