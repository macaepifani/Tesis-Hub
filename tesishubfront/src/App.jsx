import { useEffect, useState } from "react";

function App() {
    //  return <Home />;
    //EL SIGUIENTE CÓDIGO ES PARA COMUNICAR BACK Y FRONT Y VER SI ANDA...
    const [proyectos, setProyectos] = useState([]);
    const [cargando, setCargando] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        async function cargar() {
            try {
                setCargando(true);
                setError(null);

                const res = await fetch("http://localhost:8080/tesisHub/proyectos");
                if (!res.ok) throw new Error("Error HTTP: " + res.status);

                const data = await res.json();
                setProyectos(data);
            } catch (e) {
                setError(e.message);
            } finally {
                setCargando(false);
            }
        }

        cargar();
    }, []);

    if (cargando) return <p style={{ padding: 16 }}>Cargando proyectos...</p>;
    if (error) return <p style={{ padding: 16, color: "crimson" }}>Error: {error}</p>;

    return (
        <div style={{ padding: 16, fontFamily: "system-ui, Arial" }}>
            <h1 style={{ marginBottom: 12 }}>TesisHub</h1>

            {proyectos.length === 0 ? (
                <p>No hay proyectos todavía.</p>
            ) : (
                <div style={{ display: "grid", gap: 12, gridTemplateColumns: "repeat(auto-fit, minmax(260px, 1fr))" }}>
                    {proyectos.map((p) => (
                        <div
                            key={p.idProyecto}
                            style={{
                                border: "1px solid #ddd",
                                borderRadius: 12,
                                padding: 12,
                                background: "white",
                            }}
                        >
                            <h3 style={{ margin: "0 0 8px 0" }}>{p.nombre}</h3>
                            <p style={{ margin: 0, color: "#444" }}>{p.resumen}</p>
                            <p style={{ marginTop: 10, fontSize: 12, color: "#777" }}>ID: {p.idProyecto}</p>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

export default App;
