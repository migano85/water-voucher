import { useState } from "react";
import useFetch from "../useFetch";
import CustomersSearchResult from "./CustomersSearchResult";

const CustomersSearch = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [customers, setCustomers] = useState(null);
  const { error, isPending, data } = useFetch(
    "http://localhost:8081/customers/all"
  );

  const hanldeSubmit = (e) => {
    e.preventDefault();
    const customerCriteria = { firstName, lastName };

    fetch("http://localhost:8081/customers/search", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(customerCriteria),
    })
    //---------- method 1 using return promise -------------------
    .then((res) => {
      if (!res.ok) {
        alert("something went wrong");
      }
      return res.json();
    }).then((data) => {
        setCustomers(data);
      });
    //----------- method 2 using async .. await -----------------
    /*.then(async (res) => {
      if (!res.ok) {
        alert("something went wrong");
      }
      setCustomers(await res.json());
    });*/
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
    </div>
  );
};

export default CustomersSearch;
