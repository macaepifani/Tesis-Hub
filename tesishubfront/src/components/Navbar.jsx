import { Link } from "react-router-dom";
import "../styles/main.css";

export default function Navbar() {
    return (
        <nav className="navbar">
            <div className="navbar-logo">
                <Link to="/">TesisHub</Link>
            </div>

            <ul className="navbar-links">
                <li><Link to="/">Home</Link></li>
                <li><Link to="/projects">Proyectos</Link></li>
                <li><Link to="/publish">Publicar</Link></li>
                <li><Link to="/login">Login</Link></li>
            </ul>
        </nav>
    );
}
