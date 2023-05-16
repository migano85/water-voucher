import { Link } from "react-router-dom";

const HomePage = () => {
  return (
    <div>
      <Link className="linkAsButton" to="/customersSearch">
        CUSTOMERS
      </Link>
      <Link className="linkAsButton" to="/booksSearch">
        BOOKS
      </Link>
      <Link className="linkAsButton" to="/bottlesSearch">
        BOTTLES
      </Link>
    </div>
  );
};

export default HomePage;
