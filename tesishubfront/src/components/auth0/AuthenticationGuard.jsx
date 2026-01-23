import { withAuthenticationRequired } from "@auth0/auth0-react";

export default function AuthenticationGuard({ component }) {
  const Component = withAuthenticationRequired(component, {
    onRedirecting: () => <div>Redireccionando...</div>,
  });

  return <Component />;
}
