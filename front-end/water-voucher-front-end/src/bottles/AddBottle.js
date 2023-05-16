import React, { useState } from "react";
import { useHistory } from "react-router-dom";

const AddBottle = () => {
  const [serialNumber, setSerialNumber] = useState("");
  const [size, setSize] = useState("");
  const [filled, setFilled] = useState(0);

  const history = useHistory();
  const handleSubmit = (e) => {
    e.preventDefault();

    fetch("http://localhost:8080/bottles/bottle", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ serialNumber, size, filled }),
    }).then((res) => {
      if (!res.ok) {
        alert("something went wrong");
      } else {
        document.querySelector("button").disabled = true;
        history.push("/");
      }
    });
  };

  const handleCheckBox = () => {
    setFilled(!filled);
  };
  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>Serial Number:</label>
        <input
          id="txtSerialNumber"
          type="text"
          required
          value={serialNumber}
          onChange={(e) => setSerialNumber(e.target.value)}
        />
        <label>Size:</label>
        <input
          type="text"
          required
          value={size}
          onChange={(e) => setSize(e.target.value)}
        />
        <label htmlFor="ckFilled">is Filled</label>
        <input
          id="ckFilled"
          type="checkbox"
          value={filled}
          onChange={handleCheckBox}
        />

        <button>Add Bottle</button>
      </form>
    </div>
  );
};

export default AddBottle;
