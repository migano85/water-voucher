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
    //------------------------------------------

    fetch("http://localhost:8080/customers/search", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(customerCriteria),
    })
      .then((res) => {
        if (!res.ok) {
          alert("something went wrong");
        } else {
          //document.querySelector("button").disabled = true;
          // history.push("/");
        }
      })
      .then((data) => {
        console.log(data);
        // setCustomers((customers) => [data, ...customers]);
        console.log("data value");
        // console.log({ data });
        // console.log("customer value");
        setCustomers(data);
        // console.log({ customers });
      });
    //------------------------------------------
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

      {data && !customers && <p>we are data</p>}
      {customers && <p>we are customers</p>}
    </div>
  );
};

export default CustomersSearch;
