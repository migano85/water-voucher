import { Link } from "react-router-dom";
import { useState } from "react";
import useFetch from "../useFetch";
import axios from "axios";
import CustomersSearchResult from "./CustomersSearchResult";
import "./CustomersSearch.css";

const CustomersSearch = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [customers, setCustomers] = useState(null);

  const pageTitle = "page name";
  const s = <div>hello div {pageTitle}</div>;
  console.log(`${process.env.REACT_APP_ROOT_URL}`, "env URL");

  const { error, isPending, resData } = useFetch(
    `${process.env.REACT_APP_ROOT_URL}/customers/all`
  );

  const handleSubmit = (e) => {
    e.preventDefault();
    const customerCriteria = { firstName, lastName };

    axios
      .post(
        `${process.env.REACT_APP_ROOT_URL}/customers/search`,
        customerCriteria
      )
      .then((res) => {
        setCustomers(res.data);
      });
  };

  return (
    <div>
      <Link to="/">HOME</Link>
      <h2 className="title">Customers - {s}</h2>
      <form onSubmit={handleSubmit}>
        <table>
          <tbody>
            <tr>
              <td>
                <label>First name :</label>
              </td>
              <td>
                <input
                  id="txtCustFirstName"
                  type="text"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                />
              </td>
              <td>
                <label>Last name :</label>
              </td>
              <td>
                <input
                  id="txtCustLastName"
                  type="text"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                />
              </td>
            </tr>
            <tr>
              <td>
                <button>SEARCH</button>
              </td>
              <td>
                <Link className="linkAsButton" to="/addCustomer">
                  ADD
                </Link>
              </td>
            </tr>
          </tbody>
        </table>
      </form>

      {error && <div>{error}</div>}
      {isPending && <div>Loading...</div>}
      {resData && !customers && <CustomersSearchResult customers={resData} />}
      {customers && <CustomersSearchResult customers={customers} />}
    </div>
  );
};

export default CustomersSearch;
