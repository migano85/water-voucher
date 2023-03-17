import React, { useEffect, useState } from "react";
import useFetch from "../useFetch";
import Select from "react-select";
import "./AddBook.css";

const AddBook = () => {
  const [selectedOptions, setSelectedOptions] = useState();

  const { error, isPending, resData } = useFetch(
    "http://localhost:8080/customers/all"
  );

  const [optionList, setOptionList] = useState([]);
  console.log(resData);
  // setOptionList(resData);

  // const optionList = [
  //   { value: "red", label: "Red" },
  //   { value: "green", label: "Green" },
  //   { value: "yellow", label: "Yellow" },
  //   { value: "blue", label: "Blue" },
  //   { value: "white", label: "White" },
  // ];

  // Function triggered on selection
  function handleSelect(data) {
    setSelectedOptions(data);
  }

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
      <form>
        <table>
          <tbody>
            <tr>
              <td>
                <label>No of pages</label>
              </td>
              <td>
                <input id="txtPagesNo" type="text" required />
              </td>
            </tr>
            <tr>
              <td>
                <label>customer</label>
              </td>
              <td className="dropdown-container">
                <Select
                  options={resData}
                  getOptionLabel={(e) => e.firstName}
                  getOptionValue={(e) => e.customerId}
                  placeholder="Select customer"
                  value={selectedOptions}
                  onChange={handleSelect}
                  isSearchable={true}
                  styles={customStyles}
                />
              </td>
            </tr>
          </tbody>
        </table>
      </form>
    </div>
  );
};

export default AddBook;
