import { Link } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import LoginButton from "./auth0/LoginButton";
import LogoutButton from "./auth0/LogoutButton";
import "../styles/navbar.css";

export default function Navbar() {
  const { isAuthenticated, isLoading } = useAuth0();

  return (
    <nav className="navbar">
      <Link to="/" className="navbar-logo">
        TesisHub
      </Link>

      <div className="navbar-right">
        <ul className="navbar-links">
          <li><Link to="/">Home</Link></li>
          <li><Link to="/projects">Proyectos</Link></li>
          <li><Link to="/contact">Contactanos</Link></li>

          {isAuthenticated && (
            <li><Link to="/publish">Publicar</Link></li>
          )}
        </ul>

        <div className="navbar-auth">
          {isLoading ? (
            <span>...</span>
          ) : isAuthenticated ? (
            <LogoutButton />
          ) : (
            <LoginButton />
          )}
        </div>
      </div>
    </nav>
  );
}
