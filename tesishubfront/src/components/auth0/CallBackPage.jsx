import { useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";

export default function CallBackPage() {
  const { handleRedirectCallback } = useAuth0();
  const navigate = useNavigate();
  const ran = useRef(false);

  useEffect(() => {
    // En React 18 + StrictMode, los effects pueden ejecutarse 2 veces en dev
    if (ran.current) return;
    ran.current = true;

    (async () => {
      try {
        const result = await handleRedirectCallback();
        const returnTo = result?.appState?.returnTo ?? "/";
        navigate(returnTo, { replace: true });
      } catch (e) {
        console.error("Error procesando callback:", e);
        navigate("/", { replace: true });
      }
    })();
  }, [handleRedirectCallback, navigate]);

  return <div style={{ padding: 12 }}>Procesando login...</div>;
}
