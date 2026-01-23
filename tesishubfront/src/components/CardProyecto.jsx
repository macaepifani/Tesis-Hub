export default function CardProyecto({ proyecto }) {
    return (
        <div className="card-proyecto">
            <h3>{proyecto.titulo}</h3>
            <p>{proyecto.descripcion}</p>

            <div className="card-footer">
                <span>{proyecto.categoria}</span>
                <span>{proyecto.autor}</span>
            </div>
        </div>
    );
}
