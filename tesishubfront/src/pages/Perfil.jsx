import Profile from "../components/auth0/Profile";
import LogoutButton from "../components/auth0/LogoutButton";

export default function Perfil() {
  return (
    <div>
      <h1>Perfil</h1>
      <Profile />
      <LogoutButton />
    </div>
  );
}
