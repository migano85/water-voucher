import { Link } from "react-router-dom";
import pic404 from "./images/404.jpg";
import "./NotFound.css";

const NotFound = () => {
  return (
    <div className="NotFoundDiv">
      <Link className="toHomePageLink" to={"/"}>
        To home page
      </Link>
      <img src={pic404} className="banner" alt="bannerPlace" />;
    </div>
  );
};

export default NotFound;
