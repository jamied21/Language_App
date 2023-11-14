import { Link } from "react-router-dom";
import "../styles/Navbar.css";

const Navbar = () => {
  return (
    <div>
      <nav class="topnav">
        <Link to="/" class="active">
          Home
        </Link>
        <Link to="/languages">Languages</Link>
      </nav>
    </div>
  );
};

export default Navbar;
