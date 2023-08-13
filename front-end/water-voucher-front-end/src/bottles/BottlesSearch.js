import axios from "axios";
import { createContext, useState } from "react";
import useFetch from "../useFetch";
import { Link } from "react-router-dom";
import BottlesSearchResult from "./BottlesSearchResult";

export const BottlesContext = createContext();

function BottlesSearch() {
  const [bottles, setBottles] = useState([]);
  const [serialNumber, setSerialNumber] = useState("");

  const { error, isPending, resData } = useFetch(
    "http://localhost:8080/bottles/all"
  );

  const handleSubmit = (e) => {
    e.preventDefault();

    axios
      .post("http://localhost:8080/bottles/search", { serialNumber })
      .then((res) => {
        setBottles(res.data);
      });
  };

  return (
    <div>
      <Link to="/">HOME</Link>
      <h2 className="title">Bottles</h2>
      <form onSubmit={handleSubmit}>
        <table>
          <tbody>
            <tr>
              <td>
                <label>Serial Number :</label>
              </td>
              <td>
                <input
                  id="txtSerialNumber"
                  type="text"
                  value={serialNumber}
                  onChange={(e) => setSerialNumber(e.target.value)}
                />
              </td>
            </tr>
            <tr>
              <td>
                <button>SEARCH</button>
              </td>
              <td>
                <Link className="linkAsButton" to="/addBottle">
                  ADD
                </Link>
              </td>
            </tr>
          </tbody>
        </table>
      </form>

      {error && <div>{error}</div>}
      {isPending && <div>Loading...</div>}

      {resData && !bottles && (
        <BottlesSearchResult bottlesFromUseFetch={resData} />
      )}
      <BottlesContext.Provider value={bottles}>
        {bottles && <BottlesSearchResult />}
      </BottlesContext.Provider>
    </div>
  );
}

export default BottlesSearch;
