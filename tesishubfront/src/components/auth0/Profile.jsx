import { useAuth0 } from "@auth0/auth0-react";

export default function Profile() {
  const { user, isAuthenticated, isLoading } = useAuth0();

  if (isLoading) return <div>Loading...</div>;

  if (!isAuthenticated) return null;

  return (
    <div>
      <img src={user?.picture} alt={user?.name} width={48} height={48} />
      <h2>{user?.name}</h2>
      <p>{user?.email}</p>
    </div>
  );
}
