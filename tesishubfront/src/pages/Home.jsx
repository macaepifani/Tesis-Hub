import CardProyecto from "../components/CardProyecto";
import "../styles/home.css";
import heroImg from "../assets/images/social-media-people-connection-concept-vector.jpg";


const proyectosMock = [
  {
    id: 1,
    titulo: "Sistema de Gestión Universitaria",
    descripcion: "Plataforma para administrar alumnos, materias y docentes.",
    categoria: "Educación",
    autor: "Juan Pérez",
  },
  {
    id: 2,
    titulo: "App de Seguimiento de Hábitos",
    descripcion: "Aplicación móvil para mejorar la productividad personal.",
    categoria: "Salud",
    autor: "María López",
  },
  {
    id: 3,
    titulo: "Marketplace Solidario",
    descripcion: "Espacio para conectar emprendedores con impacto social.",
    categoria: "Social",
    autor: "Lucía Gómez",
  },
];

export default function Home() {
  return (
    <div className="home">

      {/* HERO */}
      <section className="hero">
        <div className="hero-text">
            <h1>
            Proyectos reales, creados por <br />
            estudiantes
            </h1>

            <p>
            Descubrí, explorá y apoyá proyectos creados por estudiantes,
            o publicá los tuyos y ganá visibilidad.
            </p>

            <button className="btn-black">Ver más</button>
        </div>

        <div className="hero-image">
            <img
            src= {heroImg}
            alt="Hero"
            />
        </div>
        </section>

      {/* LISTADO */}
       <section className="proyectos-section">
        <h2>Proyectos destacados</h2>

        <div className="proyectos-grid">
          {proyectosMock.map((proyecto) => (
            <CardProyecto key={proyecto.id} proyecto={proyecto} />
          ))}
        </div>
      </section>

    </div>
  );
}
