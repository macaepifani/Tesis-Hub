import { Routes, Route } from "react-router-dom";
import CallbackPage from "../components/auth0/CallBackPage";
import AuthenticationGuard from "../components/auth0/AuthenticationGuard";

// Ejemplo de pages (crealas si no existen)
import Home from "../pages/Home";
import Perfil from "../pages/Perfil";

export default function AppRouter() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/callback" element={<CallbackPage />} />
      <Route
        path="/perfil"
        element={<AuthenticationGuard component={Perfil} />}
      />
      <Route path="*" element={<div>404</div>} />
    </Routes>
  );
}

