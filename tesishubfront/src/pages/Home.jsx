import CardProyecto from "../components/CardProyecto";

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
            <h1>TesisHub</h1>

            <div className="grid-proyectos">
                {proyectosMock.map((proyecto) => (
                    <CardProyecto
                        key={proyecto.id}
                        proyecto={proyecto}
                    />
                ))}
            </div>
        </div>
    );
}
