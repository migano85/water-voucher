import React from "react";
import { Link } from "react-router-dom";
import editImg from "../images/icons/edit-row-48.png";
import trashImg from "../images/icons/trash-48.png";
import axios from "axios";

const BottlesSearchResult = ({ bottles }) => {
  const handleDelete = (id) => {
    alert(id);
    // DELETE request using axios with error handling
    axios
      .delete("http://localhost:8080/bottles/" + id)
      .then((response) => {
        alert("Delete successful");
        console.log(response);
      })
      .catch((error) => {
        alert(error.message);
        console.error("There was an error!", error);
      });
  };
  return (
    <div>
      <table>
        <tbody>
          <tr className="headerRow" key="0">
            <td width={300}>serial Number</td>
            <td width={300}>size</td>
            <td width={300}>is full</td>
            <td>Edit</td>
            <td>Delete</td>
          </tr>
          {bottles.map((bottle) => (
            <tr key={bottle.bottleId}>
              <td>{bottle.serialNumber}</td>
              <td>{bottle.size}</td>
              <td></td>
              <td>
                <Link to={`/bottles/${bottle.bottleId}`}>
                  <img src={editImg} alt="edit" />
                </Link>
              </td>
              <td>
                <img
                  src={trashImg}
                  alt="delete"
                  onClick={(e) => handleDelete(bottle.bottleId)}
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default BottlesSearchResult;
