import React, { useEffect, useState } from "react";
import { Link, useHistory } from "react-router-dom";
import useFetch from "../useFetch";
import Select from "react-select";
import "./AddBook.css";

const AddBook = () => {
  const history = useHistory();
  const [selectedOption, selectedOptionCallback] = useState();
  const [numberOfPages, setNoOfPagesValue] = useState(0);
  const [customerId, setCustomerId] = useState(0);

  const { error, isPending, resData } = useFetch(
    "http://localhost:8080/customers/all"
  );

  // Function triggered on selection
  function handleSelect(selectedValue) {
    selectedOptionCallback(selectedValue);
    setCustomerId(selectedValue.customerId);
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    const book = {
      numberOfPages,
      customerId,
    };

    fetch("http://localhost:8080/book-api/books", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(book),
    }).then((res) => {
      if (!res.ok) {
        alert("something went wrong");
        console.log(res);
      } else {
        document.querySelector("button").disabled = true;
        history.push("/");
      }
    });
  };

  const customStyles = {
    control: (provided, state) => ({
      ...provided,
      background: "#fff",
      borderColor: "#9e9e9e",
      minHeight: "30px",
      height: "30px",
      boxShadow: state.isFocused ? null : null,
    }),

    valueContainer: (provided, state) => ({
      ...provided,
      height: "30px",
      padding: "0 6px",
    }),

    input: (provided, state) => ({
      ...provided,
      margin: "0px",
    }),
    indicatorSeparator: (state) => ({
      display: "none",
    }),
    indicatorsContainer: (provided, state) => ({
      ...provided,
      height: "30px",
    }),
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <table>
          <tbody>
            <tr>
              <td>
                <label>No of pages</label>
              </td>
              <td>
                <input
                  id="txtPagesNo"
                  type="text"
                  placeholder="enter number of pages"
                  onChange={(e) => setNoOfPagesValue(e.target.value)}
                  required
                />
              </td>
            </tr>
            <tr>
              <td>
                <label>
                  customer {selectedOption != null && selectedOption.firstName}
                </label>
              </td>
              <td>
                <Select
                  options={resData}
                  getOptionLabel={(e) => e.firstName + " " + e.lastName}
                  getOptionValue={(e) => e}
                  placeholder="Select customer"
                  value={selectedOption}
                  onChange={handleSelect}
                  isSearchable={true}
                  styles={customStyles}
                  // isMulti
                />
              </td>
            </tr>
          </tbody>
        </table>
        <button>Add Book</button>
        <Link className="linkAsButton" to="/">
          Customers
        </Link>
      </form>
    </div>
  );
};

export default AddBook;
