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
  const [todos, setTodos] = useState([]);

  const pageTitle = "page name";
  const s = <div>hello div {pageTitle}</div>;

  const { error, isPending, resData } = useFetch(
    "http://localhost:8080/customers/all"
  );

  const handleSubmit = (e) => {
    e.preventDefault();
    const customerCriteria = { firstName, lastName };

    axios
      .post("http://localhost:8080/customers/search", customerCriteria)
      .then((res) => {
        setCustomers(res.data);
      });
  };

  const addTodo = () => {
    setTodos((t) => [...t, "New Todo"]);
  };

  return (
    <div>
      <Link to="/">HOME</Link>
      <h2 className="title">Customers - {s}</h2>
      <div>
        {todos.map((todo, index) => {
          return <p key={index}>{todo}</p>;
        })}
      </div>
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
                <button onClick={addTodo}> todo</button>
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
