import { Link } from "react-router-dom";
import "../styles/AllStyles.css";
import DarkModeToggle from "./DarkModeToggle";

const Navbar = () => {
  return (
    <div>
      <nav class="topnav">
        <Link to="/" class="active">
          Home
        </Link>
        <DarkModeToggle />
      </nav>
    </div>
  );
};

export default Navbar;
