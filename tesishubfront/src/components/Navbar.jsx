import { Link } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import LoginButton from "./auth0/LoginButton";
import LogoutButton from "./auth0/LogoutButton";
import "../styles/main.css";

export default function Navbar() {
  const { isAuthenticated, isLoading } = useAuth0();

  return (
    <nav className="navbar">
      <div className="navbar-logo">
        <Link to="/">TesisHub</Link>
      </div>

      <ul className="navbar-links">
        <li><Link to="/">Home</Link></li>
        <li><Link to="/projects">Proyectos</Link></li>

        {/* publicar suele ser algo protegido */}
        {isAuthenticated && <li><Link to="/publish">Publicar</Link></li>}

        <li>
          {isLoading ? (
            <span>...</span>
          ) : isAuthenticated ? (
            <LogoutButton />
          ) : (
            <LoginButton />
          )}
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;
