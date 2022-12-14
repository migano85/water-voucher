import { useState } from "react";
import useFetch from "../useFetch";
import CustomersSearchResult from "./CustomersSearchResult";

const CustomersSearch = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [customers, setCustomers] = useState(null);
  const { error, isPending, data } = useFetch(
    "http://localhost:8081/customers"
  );

  const hanldeSubmit = (e) => {
    e.preventDefault();
    const customerCriteria = { firstName, lastName };
    console.log(JSON.stringify(customerCriteria));

    setCustomers(data);
    console.log("data value is");
    console.log(data);
    console.log("customers values is ");
    console.log(customers);
  };
  return (
    <div>
      <h2 className="title">Customers</h2>
      <form onSubmit={hanldeSubmit}>
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
              <td colSpan="2">
                <button>search</button>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
      {error && <div>{error}</div>}
      {isPending && <div>Loading...</div>}
      {data && !customers && <CustomersSearchResult customers={data} />}
      {customers && <CustomersSearchResult customers={customers} />}
    </div>
  );
};

export default CustomersSearch;
